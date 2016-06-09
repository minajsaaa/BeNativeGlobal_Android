package com.smatoos.b2b.activity;

import com.smatoos.b2b.R;
import com.smatoos.nobug.activity.PendingActivity;

public class MainActivity extends PendingActivity {

    //  =====================================================================================

    @Override
    public int getLayoutContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void createChildren() {

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
}
