package com.example.hiotmvp.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hiotmvp.injector.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPreferenceHelper {
    private SharedPreferences sp;
    private static final String PREF_KEY_USER_TOKEN = "PREF_KEY_USER_TOKEN";

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


    public void setUserToken(String token) {
        sp.edit().putString(PREF_KEY_USER_TOKEN, token).apply();
    }

    public String getToken() {
        return sp.getString(PREF_KEY_USER_TOKEN, "");
    }


}
