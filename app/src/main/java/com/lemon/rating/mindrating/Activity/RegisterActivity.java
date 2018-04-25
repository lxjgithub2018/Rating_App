package com.lemon.rating.mindrating.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lemon.rating.mindrating.Bean.JsonSignupBean;
import com.lemon.rating.mindrating.Other.CONFIG;
import com.lemon.rating.mindrating.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText editUserName;
    private EditText editPassWord;
    private EditText editPassWordAgain;
    private Button btnLogin;
    private TextView tvLogin;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);
        editUserName=(EditText)findViewById(R.id.et_account);
        editPassWord=(EditText)findViewById(R.id.et_password1);
        editPassWordAgain=(EditText)findViewById(R.id.et_password2);
        btnLogin=(Button)findViewById(R.id.btn_login);
        tvLogin=(TextView)findViewById(R.id.tv_login);

    }

    @Override
    protected void initEvent() {
        btnLogin.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                signup();
                break;
            case R.id.tv_login:
                //点击登录连接，跳转到登录页面
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            default:
                break;
        }
    }


    public void signup() {
        //判断是否合法
        if (!validate()) {
            onSignupFailed(0);
            return;
        }
        btnLogin.setEnabled(false);

        //显示圆形进度条对话框
        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("创建账号...");
        progressDialog.show();
        //获取数据
        String username = editUserName.getText().toString();
        String password = editPassWord.getText().toString();
//      联网获取数据
        OkGo.get(CONFIG.URL_SIGNUP)
                .params("username",username)
                .params("password",password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        JsonSignupBean jsonSignupBean = gson.fromJson(s, JsonSignupBean.class);
                        //如果得到返回消息为ok,则注册成功。
                        if (jsonSignupBean.getMsg().equals("ok")){
                            Log.e("lxj", "onSuccess: 注册成功" );
                            onSignupSuccess();
                            //对话框消失
                            progressDialog.dismiss();
                        }else{
                            onSignupFailed(1);
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    /**
     * 登陆成功
     */
    public void onSignupSuccess() {
        btnLogin.setEnabled(true);
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 注册失败，按钮置为可用
     * 依据传参不同，进行不同吐司
     */
    public void onSignupFailed(int i) {
        if(i==1){
            Toast.makeText(getBaseContext(), "该用户名已经被注册", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getBaseContext(), "注册失败", Toast.LENGTH_LONG).show();
        }
        btnLogin.setEnabled(true);
    }

    /**
     *
     * @return 输入内容是否合法
     */
    public boolean validate() {
        boolean valid = true;
//      从控件中获取数据
        String name = editUserName.getText().toString();
        String password = editPassWord.getText().toString();
        String reEnterPassword = editPassWordAgain.getText().toString();
        //检测账号是否正确
        if (name.isEmpty()) {
            editUserName.setError("账号不能为空");
            valid = false;
        } else {
            editUserName.setError(null);
        }
        //检测密码是否正确
        if (password.isEmpty()) {
            editPassWord.setError("请输入密码");
            valid = false;
        } else {
            editPassWord.setError(null);
        }
        //检测重复密码是否正确
        if (reEnterPassword.isEmpty() || !(reEnterPassword.equals(password))) {
            editPassWordAgain.setError("两次密码不一致");
            valid = false;
        } else {
            editPassWordAgain.setError(null);
        }
        return valid;
    }
}
