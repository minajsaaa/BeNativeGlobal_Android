package com.smatoos.b2b.activity;

import com.smatoos.b2b.R;
import com.smatoos.nobug.core.BaseActivity;
import com.smatoos.nobug.net.RetrofitBuilder;
import com.smatoos.nobug.util.ObjectUtil;
import com.smatoos.nobug.util.log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SMATOOS_10 on 2016-06-09.
 */
public class SplashActivity extends BaseActivity implements Callback {

    //  ======================================================================================

    @Override
    public int getLayoutContentView() {
        return R.layout.activity_splash;
    }

    @Override
    public void createChildren() {

        log.show("create children");

        RetrofitBuilder.with(getContext()).getVersion().enqueue(this);
    }

    @Override
    public void setProperties() {
        super.setProperties();

        if (getIntent() != null) {

        }
    }

    @Override
    public void configureListener() {
        super.configureListener();
    }

    //  ========================================================================================

    @Override
    public void onResponse(Call call, Response response) {
        log.show("onResponse : " + ObjectUtil.toProperties(response.body()) );
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        log.show("onFailure : " + t.getLocalizedMessage() );
    }

}
