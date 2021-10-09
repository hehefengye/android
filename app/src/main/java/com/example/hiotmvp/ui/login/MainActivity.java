package com.example.hiotmvp.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hiotmvp.R;
import com.example.hiotmvp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @Inject
    MainPresenter mainPresenter;

    /*@BindView(R.id.btn)
    Button button;

    @BindView(R.id.btn1)
    Button button1;*/

    Unbinder unbinder;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.btn1)
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }

    /*@OnClick({R.id.btn, R.id.btn1})
    public void onClick(View view) {
        mainPresenter.fetch();
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void injectDependence() {
        getActivityComponent().inject(this);
    }

    @Override
    public MainPresenter createPresenter() {
        return this.mainPresenter;
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @OnClick({R.id.btn, R.id.btn1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                mainPresenter.fetch();
                break;
            case R.id.btn1:
                mainPresenter.fetch();
                break;
        }
    }
}
