package com.example.hiotmvp.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hiotmvp.R;
import com.example.hiotmvp.ui.base.BaseActivity;
import com.example.hiotmvp.ui.login.LoginActivity;
import com.example.hiotmvp.ui.main.MainActivity;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter> implements  RegisterView {

    @Inject
    RegisterPresenter registerPresenter;

    @BindView(R.id.btn_register)
    Button buttonRegister;
    @BindView(R.id.tiptet_user_name)
    TextInputEditText editTextUserName;
    @BindView(R.id.tiptet_email)
    TextInputEditText editTextEmail;
    @BindView(R.id.tiptet_password)
    TextInputEditText editTextPassword;
    @BindView(R.id.tv_link_login)
    TextView textView;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        unbinder = ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_register, R.id.tv_link_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String email = editTextEmail.getText().toString();
                registerPresenter.register(userName, password, email);
                break;
            case R.id.tv_link_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void injectDependence() {
        getActivityComponent().inject(this);
    }

    @Override
    public RegisterPresenter createPresenter() {
        return this.registerPresenter;
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerSuccess(String userName, String password) {
        registerPresenter.login(userName, password);
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        //跳转到主界面
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
