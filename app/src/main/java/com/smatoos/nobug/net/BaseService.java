package com.smatoos.nobug.net;

import com.smatoos.b2b.model.VersionItem;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface BaseService {

    //  ========================================================================================

//    @FormUrlEncoded
    @GET("setting/version")
    Call<VersionItem> getVersion();

/*
    @GET("/setting/version")
    Call<VersionItem> getAreaGroup(@Path("apiVersion") String apiVersion, @Query("address") String address);
*/

}
