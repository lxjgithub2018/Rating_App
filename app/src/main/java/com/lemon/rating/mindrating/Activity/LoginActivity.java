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
import com.lemon.rating.mindrating.Bean.JsonLoginBean;
import com.lemon.rating.mindrating.Other.CONFIG;
import com.lemon.rating.mindrating.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText editUserName;
    private EditText editPassWord;
    private Button btnLogin;
    private TextView tvRegister;
    private TextView tvFgPassWord;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        editUserName=(EditText)findViewById(R.id.et_account);
        editPassWord=(EditText)findViewById(R.id.et_password);
        btnLogin=(Button)findViewById(R.id.btn_login);
        tvRegister=(TextView)findViewById(R.id.tv_register);
        tvFgPassWord=(TextView)findViewById(R.id.tv_password);
    }

    @Override
    protected void initEvent() {
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvFgPassWord.setOnClickListener(this);
    }


    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_register:
                toRegistPage();
                break;
            case R.id.tv_password:

                break;
            default:
                break;
        }
    }

    /**
     * 登录方法
     */
    public void login() {
        //如果内容不合法，则直接返回，显示错误。
        if (!validate()) {
            onLoginFailed();
            return;
        }
        //输入合法，将登录按钮置为不可用，显示圆形进度对话框
        btnLogin.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("登录中...");
        progressDialog.show();
        //获取输入内容
        String username = editUserName.getText().toString().trim();
        String password = editPassWord.getText().toString().trim();
        //联网，获取数据
        OkGo.get(CONFIG.URL_LOGIN)
                .params("username", username)
                .params("password", password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, okhttp3.Call call, Response response) {
                        Gson gson = new Gson();
                        JsonLoginBean jsonLoginBean = gson.fromJson(s, JsonLoginBean.class);
                        //如果得到权限>0,则登录成功。
                        if (jsonLoginBean.getPermission() >= 0) {
                            Log.e("lxj", "onSuccess: ===");
                            onLoginSuccess();
                            progressDialog.dismiss();
                        } else {
                            onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    /**
     * 判断是否账号密码是否合法
     */
    public boolean validate() {
        //设置初值，默认为合法
        boolean valid = true;

        //获取输入内容
        String username = editUserName.getText().toString().trim();
        String password = editPassWord.getText().toString().trim();

        //判断账号
        if (username.isEmpty()) {
            editUserName.setError("请输入你的账号");
            valid = false;
        } else {
            editUserName.setError(null);
        }
        //判断密码
        if (password.isEmpty()) {
            editPassWord.setError("请输入你的密码");
            valid = false;
        } else {
            editPassWord.setError(null);
        }

        return valid;
    }

    /**
     * 登录成功
     */
    public void onLoginSuccess() {
        btnLogin.setEnabled(true);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("userName",editUserName.getText().toString().trim());
        startActivity(intent);
        Toast.makeText(getBaseContext(), "登陆成功", Toast.LENGTH_LONG).show();
        finish();
    }

    /**
     * 登录失败
     */
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "登陆失败", Toast.LENGTH_LONG).show();
        btnLogin.setEnabled(true);
    }

    /**
     * 跳转到主页
     */
    private void toMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到注册
     */
    private void toRegistPage() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
