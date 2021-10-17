package com.example.hiotmvp.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hiotmvp.R;
import com.example.hiotmvp.ui.base.BaseActivity;
import com.example.hiotmvp.ui.main.MainActivity;
import com.example.hiotmvp.ui.register.RegisterActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.btn_login)
    Button buttonLogin;
    @BindView(R.id.tiptet_email)
    TextInputEditText editTextEmail;
    @BindView(R.id.tiptet_password)
    TextInputEditText editTextPassword;
    Unbinder unbinder;

    @BindView(R.id.tv_link_signup)
    TextView tvLinkSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.tv_link_signup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String password = editTextPassword.getText().toString();
                String userName = editTextEmail.getText().toString();
                loginPresenter.login(userName, password);
                break;
            case R.id.tv_link_signup:
                Intent intent = new Intent(this, RegisterActivity.class);
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
    public LoginPresenter createPresenter() {
        return loginPresenter;
    }

    @Override
    public void loginSuccess() {
        //跳转到主界面
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
