package com.lemon.rating.mindrating.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemon.rating.mindrating.Activity.BaseActivity;


/**
 * Created by Administrator on 2018/2/19.
 */

public abstract class BaseFragment extends Fragment {
    protected View mRootView;
    protected BaseActivity mActivity;
    private boolean fragmentCreated = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = (BaseActivity) getActivity();
        View view = initView(inflater, container);
        initEvent();
        initData();
        mRootView = view;
        return view;
    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    protected abstract void initEvent();

    protected abstract void initData();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentCreated = true;
    }

    public boolean isFragmentCreated() {
        return fragmentCreated;
    }
}
