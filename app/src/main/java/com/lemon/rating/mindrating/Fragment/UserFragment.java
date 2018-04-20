package com.lemon.rating.mindrating.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemon.rating.mindrating.R;


/**
 * Created by Administrator on 2018/4/19.
 */

public class UserFragment extends BaseFragment {

    public UserFragment() {
        // Required empty public constructor
    }
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.user_fragment, container, false);
        return view;
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
