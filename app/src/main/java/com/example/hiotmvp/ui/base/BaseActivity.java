package com.example.hiotmvp.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hiotmvp.app.App;
import com.example.hiotmvp.injector.component.ActivityComponent;
import com.example.hiotmvp.injector.component.DaggerActivityComponent;

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity implements BaseView  {
    protected P basePresenter;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependence();
        basePresenter = createPresenter();
        if (basePresenter != null) {
            basePresenter.setView((V) this);
        }
    }

    public ActivityComponent getActivityComponent() {
        if (null == activityComponent) {
            activityComponent = DaggerActivityComponent.builder().applicationComponent(((App)getApplication()).component()).build();
        }
        return activityComponent;
    }

    public abstract void injectDependence();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (basePresenter != null) {
            basePresenter.detach();
        }
    }

    public abstract P createPresenter();
}
