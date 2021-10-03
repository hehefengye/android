package com.example.hiotmvp.injector.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hiotmvp.app.App;
import com.example.hiotmvp.data.DataManager;
import com.example.hiotmvp.data.SharedPreferenceHelper;
import com.example.hiotmvp.injector.ApplicationContext;
import com.example.hiotmvp.injector.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    public void inject(App application);

    @ApplicationContext
    public Context context();

    public DataManager dataManager();
    public SharedPreferenceHelper sharedPreferenceHelper();
}
