package com.lemon.rating.mindrating.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lemon.rating.mindrating.Activity.BaseActivity;
import com.lemon.rating.mindrating.Bean.ProjectBean;
import com.lemon.rating.mindrating.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/23.
 */

public class CommitAdapter extends BaseAdapter {
    protected BaseActivity mActivity;
    private Context context;
    private List<ProjectBean> data ;

    public CommitAdapter(Context context, List<ProjectBean> data) {
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
        if(convertView==null){

            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout, null);

        }
        ImageView iv_img = (ImageView) convertView.findViewById(R.id.img);
        TextView tv_name = (TextView) convertView.findViewById(R.id.title);
        TextView tv_desc = (TextView) convertView.findViewById(R.id.deail);
        TextView tv_type = (TextView) convertView.findViewById(R.id.type);

        ProjectBean Project = data.get(position);

        if(Project.getImgPath()==null){
            iv_img.setImageResource(R.drawable.avatar_default_1);
        }else{
           /* //byte [] imgData = Project.getImgPath();
            Bitmap bm  = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
            iv_img.setImageBitmap(bm);*/
        }

        tv_name.setText( Project.getProjectName());
        tv_desc.setText( Project.getProjectDescribe());

        return convertView;
    }
}
