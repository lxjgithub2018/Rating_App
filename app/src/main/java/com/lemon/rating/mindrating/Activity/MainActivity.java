package com.lemon.rating.mindrating.Activity;


import android.support.v4.app.FragmentTabHost;
import android.widget.TabHost;


import com.lemon.rating.mindrating.Fragment.MainFragment;
import com.lemon.rating.mindrating.Fragment.UserFragment;
import com.lemon.rating.mindrating.R;
import com.lemon.rating.mindrating.other.TabHostItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private List<TabHostItem> mFragmentList;
    private FragmentTabHost mFragmentTabHost;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        initTabItemData();
    }

    /**
     * 初始化 Tab 数据
     */
    private void initTabItemData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new TabHostItem(
                R.drawable.icon_caption_normal,
                R.drawable.icon_caption_selected,
                this.getResources().getString(R.string.caption_fragment),
                MainFragment.class
        ));

        mFragmentList.add(new TabHostItem(
                R.drawable.icon_contacts_normal,
                R.drawable.icon_contacts_selected,
                this.getResources().getString(R.string.contacts_fragment),
                UserFragment.class
        ));

        mFragmentList.add(new TabHostItem(
                R.drawable.icon_more_normal,
                R.drawable.icon_more_selected,
                this.getResources().getString(R.string.more_fragment),
                UserFragment.class
        ));

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
               /* if(tabId.equals(getResources().getString(R.string.caption_fragment))){
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.gray1));
                    search_layout.setVisibility(View.VISIBLE);
                    toolbarTitle.setVisibility(View.GONE);
                }else if(tabId.equals(getResources().getString(R.string.home_fragment))){
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.gray));
                    search_layout.setVisibility(View.VISIBLE);
                    toolbarTitle.setVisibility(View.GONE);
                }else if(tabId.equals(getResources().getString(R.string.more_fragment))){
                    toolbarTitle.setText(getResources().getString(R.string.more_fragment));
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    search_layout.setVisibility(View.GONE);
                    toolbarTitle.setVisibility(View.VISIBLE);
                }else if(tabId.equals(getResources().getString(R.string.user_fragment))){
                    toolbarTitle.setText(getResources().getString(R.string.user_fragment));
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    search_layout.setVisibility(View.GONE);
                    toolbarTitle.setVisibility(View.VISIBLE);
                }*/
            }
        });
    }
}
