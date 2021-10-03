package com.example.hiotmvp.app;

import android.app.Application;

import com.example.hiotmvp.injector.component.ApplicationComponent;
import com.example.hiotmvp.injector.component.DaggerApplicationComponent;
import com.example.hiotmvp.injector.module.ApplicationModule;

public class App extends Application {
    private ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent component() {
        return this.applicationComponent;
    }
}
