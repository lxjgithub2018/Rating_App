package com.lemon.rating.mindrating.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lemon.rating.mindrating.Bean.JsonLoacProJectBean;
import com.lemon.rating.mindrating.Bean.ProjectBean;
import com.lemon.rating.mindrating.Other.CONFIG;
import com.lemon.rating.mindrating.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class AddActivity extends BaseActivity implements View.OnClickListener{
    private List<String> listPlayer;
    private ArrayAdapter<String> adapterPlayer;

    private List<String> listItem;
    private ArrayAdapter<String> adapterItem;

    private List<String> listScore;
    private ArrayAdapter<String> adapterScore;

    private Toolbar mToolbar;
    private TextView toolbarTitle;
    private String userName;
    private Button btnAdd;

    //项目名称
    private EditText editName;

    //项目简介
    private EditText editDetail;

    //项目评分规则
    private CheckBox checkBoxRulOne;
    private CheckBox checkBoxRulTwo;

    //项目选手
    private EditText editPlayer;
    private ListView lvPlayer;
    private Button btnAddPlayer;

    //评选内容
    private EditText editItem;
    private ListView lvItem;
    private Button btnAddItem;

    //项目评委
    private EditText editScore;
    private ListView lvScore;
    private Button btnAddScore;

    private int playNum;
    private int itemNum;
    private int scoreNum;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_add_pro_ject);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText("创建项目");

        btnAdd=(Button)findViewById(R.id.btn_add) ;
        editName=(EditText)findViewById(R.id.et_pjname);
        editDetail=(EditText)findViewById(R.id.et_detail);

        checkBoxRulOne =(CheckBox)findViewById(R.id.ruls_one);
        checkBoxRulTwo =(CheckBox)findViewById(R.id.ruls_two);

        editPlayer=(EditText)findViewById(R.id.et_player);
        lvPlayer=(ListView)findViewById(R.id.lv_player);
        btnAddPlayer=(Button)findViewById(R.id.btn_player);

        editItem=(EditText)findViewById(R.id.et_item);
        lvItem=(ListView)findViewById(R.id.lv_item);
        btnAddItem=(Button)findViewById(R.id.btn_item);

        editScore=(EditText)findViewById(R.id.et_score);
        lvScore=(ListView)findViewById(R.id.lv_score);
        btnAddScore=(Button)findViewById(R.id.btn_score);
    }

    @Override
    protected void initEvent() {
        btnAdd.setOnClickListener(this);
        btnAddPlayer.setOnClickListener(this);
        btnAddItem.setOnClickListener(this);
        btnAddScore.setOnClickListener(this);
        checkBoxRulOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBoxRulTwo.setChecked(false);
                }
            }
        });

        checkBoxRulTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBoxRulOne.setChecked(false);
                }
            }
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        userName=intent.getStringExtra("userName");

        listPlayer= new ArrayList<String>();
        adapterPlayer= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        lvPlayer.setAdapter(adapterPlayer);

        listItem= new ArrayList<String>();
        adapterItem= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        lvItem.setAdapter(adapterItem);

        listScore= new ArrayList<String>();
        adapterScore= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        lvScore.setAdapter(adapterScore);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_add:
                //AddPeoJect();
                Add();
                break;
            case R.id.btn_player:
                setPlayListView(editPlayer.getText().toString().trim());
                break;
            case R.id.btn_item:
                setItemListView(editItem.getText().toString().trim());
                break;
            case R.id.btn_score:
                setScoreListView(editScore.getText().toString().trim());
                break;
            default:
                break;
        }
    }
    /**
     *
     * @return 输入内容是否合法
     */
    public boolean validate() {
        boolean valid = true;
        //从控件中获取数据
        String peoJectName=editName.getText().toString().trim();
        String peoJectDetail=editDetail.getText().toString().trim();

        //检测账号是否正确
        if (peoJectName.isEmpty()) {
            editName.setError("不能为空");
            valid = false;
        } else {
            editName.setError(null);
        }

        //检测密码是否正确
        if (peoJectDetail.isEmpty()) {
            editDetail.setError("不能为空");
            valid = false;
        } else {
            editDetail.setError(null);
        }

        return valid;
    }

    private void Add(){
        //判断是否合法
        if (!validate()) {
            Toast.makeText(this.getBaseContext(), "不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("创建中...");
        progressDialog.show();
        //获取数据
        String proectName=editName.getText().toString().trim();
        String proJectJDetail=editDetail.getText().toString().trim();
        String rules;
        List<String> listP=listPlayer;
        List<String> listI=listItem;
        List<String> listS=listScore;

        if(checkBoxRulOne.isChecked()){
            rules="十分制";
        }else {
            rules="百分制";
        }

        ProjectBean pro=new ProjectBean();
        pro.setProJectPeo(userName);
        pro.setProjectName(proectName);
        pro.setProjectDescribe(proJectJDetail);
        pro.setListPlayer(listP);
        pro.setListItem(listI);
        pro.setListScore(listS);

        Gson gson = new Gson();
        String jsonObj = gson.toJson(pro);

        OkGo.post(CONFIG.URL_AddProJect).upJson(jsonObj).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                JsonLoacProJectBean jsonLoacProJectBean = gson.fromJson(s, JsonLoacProJectBean.class);
                //如果得到返回消息为ok,则添加成功
                if (jsonLoacProJectBean.getMsg().equals("ok")) {
                    Toast.makeText(AddActivity.this.getBaseContext(), "创建成功", Toast.LENGTH_LONG).show();
                    //对话框消失
                    progressDialog.dismiss();
                    finish();
                }else if(jsonLoacProJectBean.getMsg().equals("rename")){

                    Toast.makeText(AddActivity.this.getBaseContext(), "项目名称重复", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }else {
                    Toast.makeText(AddActivity.this.getBaseContext(), "创建失败", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }
        });

    }

    /**
     * 添加项目
     */
    public void AddPeoJect() {
        //判断是否合法
        if (!validate()) {
            Toast.makeText(this.getBaseContext(), "不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("创建中...");
        progressDialog.show();

        String peoJectName=editName.getText().toString().trim();
        String peoJectDetail=editDetail.getText().toString().trim();

        //联网，获取数据
        OkGo.get(CONFIG.URL_AddProJect)
                .params("username", userName)
                .params("peoJectName",peoJectName)
                .params("peoJectDetail",peoJectDetail)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, okhttp3.Call call, Response response) {
                        Gson gson = new Gson();
                        JsonLoacProJectBean jsonLoacProJectBean = gson.fromJson(s, JsonLoacProJectBean.class);
                        //如果得到返回消息为ok,则添加成功
                        if (jsonLoacProJectBean.getMsg().equals("ok")){
                            Log.e("lxj", "onSuccess: 注册成功" );
                            Toast.makeText(AddActivity.this.getBaseContext(), "创建成功", Toast.LENGTH_LONG).show();
                            //对话框消失
                            progressDialog.dismiss();
                            finish();
                        }else{
                            Toast.makeText(AddActivity.this.getBaseContext(), "创建失败", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }


                    }
                });
    }

    private void setPlayListView(String s){
        //检测账号是否正确
        if (s.isEmpty()) {
            editPlayer.setError("不能为空");
        } else {
            editPlayer.setError(null);
            // 添加数据
            listPlayer.add(s);
            adapterPlayer.clear();
            adapterPlayer.addAll(listPlayer);
            adapterPlayer.notifyDataSetChanged();
            editPlayer.setText("");
        }
    }

    private void setItemListView(String s){
        //检测账号是否正确
        if (s.isEmpty()) {
            editItem.setError("不能为空");
        } else {

            // 添加数据
            listItem.add(s);
            adapterItem.clear();
            adapterItem.addAll(listItem);
            adapterItem.notifyDataSetChanged();
            editItem.setError(null);
            editItem.setText("");
        }
    }

    private void setScoreListView(String s){
        //检测账号是否正确
        if (s.isEmpty()) {
            editScore.setError("不能为空");
        } else {
            editScore.setError(null);
            // 添加数据
            listScore.add(s);
            adapterScore.clear();
            adapterScore.addAll(listScore);
            adapterScore.notifyDataSetChanged();
            editScore.setText("");
        }
    }
}
