package com.example.hiotmvp.injector.component;

import com.example.hiotmvp.injector.PerActivity;
import com.example.hiotmvp.injector.module.ActivityModule;
import com.example.hiotmvp.ui.login.LoginActivity;
import com.example.hiotmvp.ui.mine.MineFragment;
import com.example.hiotmvp.ui.register.RegisterActivity;

import dagger.Component;
@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    //public void inject(MainActivity mainActivity);
    public void inject(RegisterActivity registerActivity);
    public void inject(LoginActivity loginActivity);
    public void inject(MineFragment mineFragment);

}
