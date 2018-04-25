package com.lemon.rating.mindrating.Activity;


import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TabHost;
import android.widget.TextView;

import com.lemon.rating.mindrating.Fragment.MainFragment;
import com.lemon.rating.mindrating.Fragment.UserFragment;
import com.lemon.rating.mindrating.Other.TabHostItem;
import com.lemon.rating.mindrating.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private List<TabHostItem> mFragmentList;
    private FragmentTabHost mFragmentTabHost;
    private Toolbar mToolbar;
    private TextView toolbarTitle;
    private String userName;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText("项目浏览");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        getLogIntent();
        initTabItemData();
    }

    /*
    *获取Intent
    *
    * */
    private void getLogIntent(){
        //获取传来的intent对象
        Intent intent = getIntent();
        //获取键值对的键名
        userName=intent.getStringExtra("userName");
    }
    public String getUserName(){
        return userName;
    }

    /**
     * 初始化 Tab 数据
     */
    private void initTabItemData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new TabHostItem(
                R.drawable.icon_caption_normal,
                R.drawable.icon_caption_selected,
                this.getResources().getString(R.string.main_fragment),
                MainFragment.class
        ));

        /*mFragmentList.add(new TabHostItem(
                R.drawable.icon_contacts_normal,
                R.drawable.icon_contacts_selected,
                this.getResources().getString(R.string.contacts_fragment),
                FriendsFragment.class
        ));*/

        /*mFragmentList.add(new TabHostItem(
                R.drawable.icon_more_normal,
                R.drawable.icon_more_selected,
                this.getResources().getString(R.string.more_fragment),
                MoreFragment.class
        ));*/

        mFragmentList.add(new TabHostItem(
                R.drawable.icon_user_normal,
                R.drawable.icon_user_selected, this.getResources().getString(R.string.user_fragment),
                UserFragment.class
        ));

        mFragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        // 绑定 FragmentManager
        mFragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        // 删除分割线
//        mFragmentTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mFragmentList.size(); i++) {
            TabHostItem tabItem = mFragmentList.get(i);
            // 创建 tab
            TabHost.TabSpec tabSpec = mFragmentTabHost.newTabSpec(
                    tabItem.getTabText()).
                    setIndicator(tabItem.getTabView(MainActivity.this));
            // 将创建的 tab 添加到底部 tab 栏中（ @android:id/tabs ）
            // 将 Fragment 添加到页面中（ @android:id/tabcontent ）
            mFragmentTabHost.addTab(tabSpec, tabItem.getFragmentClass(), null);
            // 底部 tab 栏设置背景图片
            mFragmentTabHost.getTabWidget().setBackgroundResource(R.color.darker_gray);

            // 默认选中第一个 tab
            if (i == 0) {
                tabItem.setChecked(true);
            } else {
                tabItem.setChecked(false);
            }
        }

        // 切换 tab 时，回调此方法
        mFragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < mFragmentList.size(); i++) {
                    TabHostItem tabItem = mFragmentList.get(i);
                    // 通过 tag 检查用户点击的是哪个 tab
                    if (tabId.equals(tabItem.getTabText())) {
                        tabItem.setChecked(true);
                    } else {
                        tabItem.setChecked(false);
                    }
                }
                if(tabId.equals(getResources().getString(R.string.main_fragment))){
                    toolbarTitle.setText(getResources().getString(R.string.main_fragment));
                }/*else if(tabId.equals(getResources().getString(R.string.contacts_fragment))){
                    toolbarTitle.setText(getResources().getString(R.string.contacts_fragment));
                }else if(tabId.equals(getResources().getString(R.string.more_fragment))){
                    toolbarTitle.setText(getResources().getString(R.string.more_fragment));
                }*/else if(tabId.equals(getResources().getString(R.string.user_fragment))){
                    toolbarTitle.setText(getResources().getString(R.string.user_fragment));
                }
            }
        });
    }
}
