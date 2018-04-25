package com.lemon.rating.mindrating.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lemon.rating.mindrating.Activity.AddActivity;
import com.lemon.rating.mindrating.Activity.DetailActivity;
import com.lemon.rating.mindrating.Activity.MainActivity;
import com.lemon.rating.mindrating.Adapter.mAdapter;
import com.lemon.rating.mindrating.Bean.JsonLoacProJectBean;
import com.lemon.rating.mindrating.Bean.ProjectBean;
import com.lemon.rating.mindrating.Other.CONFIG;
import com.lemon.rating.mindrating.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;


/**
 * Created by Administrator on 2018/4/19.
 */

public class MainFragment extends BaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private mAdapter adapter;
    private ListView lvProject;
    private List<ProjectBean> data = new ArrayList<ProjectBean>();
    private String userName;
    private MainActivity mainActivity;
    private ImageButton imgAdd;
    private AutoCompleteTextView edTextView;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        lvProject=(ListView)view.findViewById(R.id.list_view);
        imgAdd=(ImageButton)view.findViewById(R.id.img_add);
        mainActivity= (MainActivity) getActivity();
        edTextView=(AutoCompleteTextView)view.findViewById(R.id.search_textview);
        mainActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return view;
    }

    @Override
    protected void initEvent() {
        lvProject.setOnItemClickListener(this);
        imgAdd.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        userName=mainActivity.getUserName();
    }

    @Override
    public void onResume() {
        super.onResume();
        LocaPeoJect();
    }

    /**
     * 加载项目
     */
    public void LocaPeoJect() {
        final ProgressDialog progressDialog = new ProgressDialog(mActivity, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("加载中...");
        progressDialog.show();
        //联网，获取数据
        OkGo.get(CONFIG.URL_LocaProJect)
                .params("username", userName)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, okhttp3.Call call, Response response) {
                        Gson gson = new Gson();
                        JsonLoacProJectBean jsonLocaPeoJect = gson.fromJson(s, JsonLoacProJectBean.class);

                        data=jsonLocaPeoJect.getProJect();
                        adapter=new mAdapter(mActivity,data);
                        lvProject.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onLoginSuccess(position);
    }

    /**
     * 项目评分页面
     */
    public void onLoginSuccess(int position) {
        Intent intent = new Intent(mActivity, DetailActivity.class);
        intent.putExtra("PeoJectName",data.get(position).getProjectName());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.img_add:
                Intent intent = new Intent(mActivity, AddActivity.class);
                intent.putExtra("userName",userName);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
