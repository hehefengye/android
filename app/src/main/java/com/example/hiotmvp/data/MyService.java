package com.example.hiotmvp.data;

import com.example.hiotmvp.data.bean.ResultResponse;
import com.example.hiotmvp.data.bean.User;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyService {
    String BASE_URL = "http://192.168.0.104:8080/";

    @GET("my.json")
    public Observable<ResultResponse<User>> getUser();
}
