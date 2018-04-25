package com.lemon.rating.mindrating.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lemon.rating.mindrating.Adapter.ScoreAdapter;
import com.lemon.rating.mindrating.Bean.JsonPlayerBean;
import com.lemon.rating.mindrating.Bean.PlayerBean;
import com.lemon.rating.mindrating.Bean.ProjectBean;
import com.lemon.rating.mindrating.Other.CONFIG;
import com.lemon.rating.mindrating.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class DetailActivity extends BaseActivity implements View.OnClickListener {
    private ScoreAdapter mScoreAdapter;
    private List<PlayerBean> data = new ArrayList<PlayerBean>();
    private String peoJectName;
    private Toolbar mToolbar;
    private TextView tvDetail;
    private TextView tvScore;
    private TextView tvCommit;
    private RelativeLayout layOutDetail;
    private RelativeLayout layOutScore;
    private RelativeLayout layOutCommit;
    private ListView lvPlayer;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_detail);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(mToolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("");
        tvDetail=(TextView)findViewById(R.id.toolbar_detal);
        tvScore=(TextView)findViewById(R.id.toolbar_score);
        tvCommit=(TextView)findViewById(R.id.toolbar_commit);
        layOutDetail=(RelativeLayout)findViewById(R.id.deail_layout);
        layOutScore=(RelativeLayout)findViewById(R.id.score_layout);
        layOutCommit=(RelativeLayout)findViewById(R.id.commit_layout);

        lvPlayer=(ListView)findViewById(R.id.lv_player_score);


    }

    @Override
    protected void initEvent() {
        tvDetail.setOnClickListener(this);
        tvScore.setOnClickListener(this);
        tvCommit.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        getLogIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLogIntent();
    }

    /**
    *获取Intent
    * */
    private void getLogIntent(){
        //获取传来的intent对象
        Intent intent = getIntent();
        //获取键值对的键名
        peoJectName=intent.getStringExtra("PeoJectName");
    }

    /**
     * 加载项目
     */
    public void LocaPeoJect() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("加载中...");
        progressDialog.show();
        //联网，获取数据
        OkGo.get(CONFIG.URL_DetailProJect)
                .params("proJect", peoJectName)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, okhttp3.Call call, Response response) {
                        Gson gson = new Gson();
                        ProjectBean PeoJect = gson.fromJson(s, ProjectBean.class);

                        progressDialog.dismiss();
                    }
                });
    }

    /**
     * 加载选手
     */
    public void LocaPlayer() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("加载中...");
        progressDialog.show();
        //联网，获取数据
        OkGo.get(CONFIG.URL_Player)
                .params("proJect", peoJectName)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, okhttp3.Call call, Response response) {
                        Gson gson = new Gson();
                        JsonPlayerBean PeoJect = gson.fromJson(s, JsonPlayerBean.class);

                        data=PeoJect.getPeoJect();
                        mScoreAdapter=new ScoreAdapter(DetailActivity.this,data);
                        lvPlayer.setAdapter(mScoreAdapter);
                        mScoreAdapter.notifyDataSetChanged();

                        progressDialog.dismiss();
                    }
                });
    }

    /**
     * 加载评论
     */
    public void LocaCommit() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("加载中...");
        progressDialog.show();
        //联网，获取数据
        OkGo.get(CONFIG.URL_DetailProJect)
                .params("proJect", peoJectName)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, okhttp3.Call call, Response response) {
                        Gson gson = new Gson();
                        JsonPlayerBean PeoJect = gson.fromJson(s, JsonPlayerBean.class);

                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_detal:
                tvDetail.setBackgroundResource(R.drawable.toolbar_ride);
                tvScore.setBackgroundResource(R.drawable.toolbar_ride_none);
                tvCommit.setBackgroundResource(R.drawable.toolbar_ride_none);
                layOutDetail.setVisibility(View.VISIBLE);
                layOutScore.setVisibility(View.GONE);
                layOutCommit.setVisibility(View.GONE);
                break;
            case R.id.toolbar_score:
                LocaPlayer();
                tvDetail.setBackgroundResource(R.drawable.toolbar_ride_none);
                tvScore.setBackgroundResource(R.drawable.toolbar_ride);
                tvCommit.setBackgroundResource(R.drawable.toolbar_ride_none);
                layOutDetail.setVisibility(View.GONE);
                layOutScore.setVisibility(View.VISIBLE);
                layOutCommit.setVisibility(View.GONE);
                break;
            case R.id.toolbar_commit:
                tvDetail.setBackgroundResource(R.drawable.toolbar_ride_none);
                tvScore.setBackgroundResource(R.drawable.toolbar_ride_none);
                tvCommit.setBackgroundResource(R.drawable.toolbar_ride);
                layOutDetail.setVisibility(View.GONE);
                layOutScore.setVisibility(View.GONE);
                layOutCommit.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
