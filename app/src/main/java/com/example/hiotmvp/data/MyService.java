package com.example.hiotmvp.data;

import com.example.hiotmvp.data.bean.LoginResultDTO;
import com.example.hiotmvp.data.bean.ResultResponse;
import com.example.hiotmvp.data.bean.User;
import com.example.hiotmvp.data.bean.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MyService {
    String BASE_URL = "http://114.67.88.191:8080/";

    @GET("my.json")
    public Observable<ResultResponse<User>> getUser();

    @POST("/user/register")
    public Observable<ResultResponse<UserBean>> register(@Body UserBean userBean);

    @FormUrlEncoded
    @POST("/auth/login")
    public Observable<ResultResponse<LoginResultDTO>> login(@Field("username")String userName, @Field("password")String password,
                                                            @Field("loginCode")String loginCode);

    @POST("/auth/logout")
    public Observable<ResultResponse> logout(@Header("Authorization")String authorization);


    @GET("/user/one")
    Observable<ResultResponse<UserBean>> getUserInfo(@Header("Authorization")String authorization);
}
