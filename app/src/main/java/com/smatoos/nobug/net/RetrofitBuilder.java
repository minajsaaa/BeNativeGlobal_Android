package com.smatoos.nobug.net;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.smatoos.nobug.constant.HeaderProperty;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static final String SERVER_URL = "https://api.benative.com/"; //2부터 url뒤에 /를 입력해야 합니다.

    // Retrofit Instance Container
    private static Map<String, BaseService> instanceContainer = new HashMap<>();

    // OkHttp Client
    private static OkHttpClient client, unsafeClient;

    /**
     * Create OkHttpClient Instance
     * @param context Context
     * @return OkHttpClient Instance
     */
    public static OkHttpClient getOkHttpClient(final Context context) {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(getInterceptor(context))
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();
        }
        return client;
    }

    //  =========================================================================================

    public static BaseService with(final Context context) {
        if (instanceContainer.containsKey(SERVER_URL)) {
            return instanceContainer.get(SERVER_URL);
        }
        BaseService baseService = new Retrofit.Builder()
                .client(getOkHttpClient(context))
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(BaseService.class);

        instanceContainer.put(SERVER_URL, baseService);
        return baseService;
    }

    public static BaseService withUnsafe(final Context context, String baseUrl) {
        if (instanceContainer.containsKey(baseUrl)) {
            return instanceContainer.get(baseUrl);
        }

        BaseService baseService = new Retrofit.Builder()
                .client(getUnsafeOkHttpClient(context))
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(BaseService.class);

        instanceContainer.put(baseUrl, baseService);
        return baseService;
    }

    private static Interceptor getInterceptor(final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header(HeaderProperty.AUTH_TOKEN, HeaderProperty.API_KEY)
/*                        .header(HeaderProperty.VERSION, DeviceInfoUtil.getAppVersion(context))
                        .header(HeaderProperty.CONTENT_TYPE, HeaderProperty.JSON_FORMAT)
                        .header(HeaderProperty.UUID, DeviceInfoUtil.getDeviceSerialNumber())
                        .header(HeaderProperty.USER_AGENT, HeaderProperty.ANDROID)*/
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        };
    }

    private static OkHttpClient getUnsafeOkHttpClient(final Context context) {
        if (unsafeClient != null) {
            return unsafeClient;
        }
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            unsafeClient = new OkHttpClient.Builder().sslSocketFactory(sslContext.getSocketFactory())
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .addInterceptor(getInterceptor(context))
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();
            return unsafeClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
