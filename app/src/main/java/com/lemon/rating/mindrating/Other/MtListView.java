package com.lemon.rating.mindrating.Other;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2018/4/25.
 */

public class MtListView extends ListView {
    public MtListView(Context context) {
        super(context);
    }

    public MtListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MtListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
