package com.smatoos.nobug.activity;

import android.os.Bundle;

import com.smatoos.b2b.R;
import com.smatoos.nobug.core.BaseActivity;

/**
 * Created by SMATOOS_10 on 2016-06-09.
 */
public class PendingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.right_in, R.anim.scale_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.scale_in, R.anim.right_out);
    }
}
