package com.lemon.rating.rating_app.other;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lemon.rating.rating_app.R;


/**
 * Created by Administrator on 2018/4/19.
 */

public class TabHostItem {
    private ImageView mIvTab;

    /**
     * 正常状态的图片
     */
    private int imageNormal;

    /**
     * 选中状态的图片
     */
    private int imageSelected;

    private TextView mTvTab;

    /**
     * 文字
     */
    private String tabText;

    /**
     * Fragment
     */
    private Class<? extends Fragment> fragmentClass;

    private View mTabView;

    public TabHostItem(int imageNormal, int imageSelected, String text, Class<? extends Fragment> fragmentClass) {
        this.imageNormal = imageNormal;
        this.imageSelected = imageSelected;
        this.tabText = text;
        this.fragmentClass = fragmentClass;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }

    /**
     * 获取 tab 上的文字
     *
     * @return tab 上的文字
     */
    public String getTabText() {
        return tabText;
    }

    /**
     * 设置选中
     *
     * @param checked 是否选中
     */
    public void setChecked(boolean checked) {
        if (checked) {
            mTvTab.setTextColor(Color.parseColor("#000000"));
            mIvTab.setImageResource(imageSelected);
        } else {
            mTvTab.setTextColor(Color.parseColor("#9b9b9b"));
            mIvTab.setImageResource(imageNormal);
        }
    }

    public View getTabView(Context context) {
        mTabView = View.inflate(context, R.layout.view_tab, null);
        mIvTab = (ImageView) mTabView.findViewById(R.id.iv_tab);
        mTvTab = (TextView) mTabView.findViewById(R.id.tv_tab);
        mIvTab.setImageResource(imageNormal);
        mTvTab.setText(tabText);
        return mTabView;
    }
}
