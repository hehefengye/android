package com.example.hiotmvp.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hiotmvp.injector.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPreferenceHelper {
    private SharedPreferences sp;

    @Inject
    public SharedPreferenceHelper(@ApplicationContext Context context) {
        this.sp = context.getSharedPreferences("userConfig", Context.MODE_PRIVATE);
    }

    public void putUserId(String userId) {
        sp.edit().putString("UserId", userId).apply();
    }

    public void clear() {
        sp.edit().clear().apply();
    }


}
