package com.lemon.rating.mindrating.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lemon.rating.mindrating.Activity.BaseActivity;
import com.lemon.rating.mindrating.Bean.PlayerBean;
import com.lemon.rating.mindrating.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/23.
 */

public class ScoreAdapter extends BaseAdapter {
    protected BaseActivity mActivity;
    private Context context;
    private List<PlayerBean> data ;

    public ScoreAdapter(Context context, List<PlayerBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.score_layout, null);

        }
        TextView tv_name = (TextView) convertView.findViewById(R.id.title);
        TextView tv_desc = (TextView) convertView.findViewById(R.id.score);

        PlayerBean Project = data.get(position);

        tv_name.setText( Project.getPlayer());
        tv_desc.setText( Project.getScore());

        return convertView;
    }
}
