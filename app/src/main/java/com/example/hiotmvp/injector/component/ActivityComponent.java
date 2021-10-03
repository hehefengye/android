package com.example.hiotmvp.injector.component;

import com.example.hiotmvp.injector.PerActivity;
import com.example.hiotmvp.injector.module.ActivityModule;
import com.example.hiotmvp.ui.login.MainActivity;

import dagger.Component;
@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    public void inject(MainActivity mainActivity);

}
