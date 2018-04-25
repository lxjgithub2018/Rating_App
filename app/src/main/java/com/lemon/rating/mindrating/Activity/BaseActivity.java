package com.lemon.rating.mindrating.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lemon.rating.mindrating.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected static List<BaseActivity> mActivityList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initEvent();
        initData();
    }
    /*@Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initDialog();
        if (!(this instanceof LoginActivity)) {
            int pos = mActivityList.indexOf(this);
            if (pos >= 0) {
                mActivityList.set(pos, this);
            } else {
                mActivityList.add(this);
            }
        }
        //initPermissions();
    }*/

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化事件
     */
    protected abstract void initEvent();

    /**
     * 初始化数据
     */
    protected abstract void initData();


    /**
     * 加载头像
     *
     * @param image
     * @param imageView
     */
    public void loadAvatar(String image, ImageView imageView) {
        loadImage(image, imageView, R.drawable.avatar_default_1);
    }

    /**
     * 加载图片
     *
     * @param image
     * @param imageView
     * @param error
     */
    public void loadImage(final String image, final ImageView imageView, final int error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(BaseActivity.this).load(image)
                        .error(error)
                        .crossFade()
                        .into(imageView);
            }
        });
    }

}
