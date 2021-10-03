package com.example.hiotmvp.data;

import com.example.hiotmvp.data.bean.ResultResponse;
import com.example.hiotmvp.data.bean.User;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class DataManager {

    private MyService myService;
    private SharedPreferenceHelper helper;

    @Inject
    public DataManager(MyService myService, SharedPreferenceHelper helper) {
        this.myService = myService;
        this.helper = helper;
    }

    public Observable<ResultResponse<User>> getUser() {
        return myService.getUser();
    }



}
