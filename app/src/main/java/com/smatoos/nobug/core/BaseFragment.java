package com.smatoos.nobug.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

public abstract class BaseFragment extends Fragment implements IBaseView {

    protected View mView;

    //  ========================================================================================

    public BaseFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = ( getLayoutContentView() > 0 )  ? inflater.inflate(getLayoutContentView(), container, false) : super.onCreateView(inflater, container, savedInstanceState);
        initialize();
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        try {
            Glide.get(getContext()).clearMemory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  =========================================================================================

    @Override
    public int getLayoutContentView() {
        return 0;
    }

    @Override
    public void initialize() {
        createChildren();
        setProperties();
        configureListener();
    }

    @Override
    public void createChildren() {
    }

    @Override
    public void configureListener() {
    }

    @Override
    public void setProperties() {
    }

}
