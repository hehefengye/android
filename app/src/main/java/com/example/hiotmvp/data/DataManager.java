package com.example.hiotmvp.data;

import com.example.hiotmvp.data.bean.LoginResultDTO;
import com.example.hiotmvp.data.bean.ResultResponse;
import com.example.hiotmvp.data.bean.User;
import com.example.hiotmvp.data.bean.UserBean;
import com.example.hiotmvp.utils.Constants;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

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

    public Observable<ResultResponse<UserBean>> register(String userName, String password, String email) {
        UserBean userBean = new UserBean();
        userBean.setEmail(email);
        userBean.setPassword(password);
        userBean.setUsername(userName);
        userBean.setUserType(Constants.REGISTER_TYPE_NORMAL);
        return  myService.register(userBean);
    }

    public Observable<ResultResponse<LoginResultDTO>> login(String userName, String password) {
       return myService.login(userName, password, Constants.LOGIN_CODE_APP).doOnNext(new Consumer<ResultResponse<LoginResultDTO>>() {
           @Override
           public void accept(ResultResponse<LoginResultDTO> loginResultDTOResultResponse) throws Exception {
               if (loginResultDTOResultResponse.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                   if (loginResultDTOResultResponse != null && loginResultDTOResultResponse.getData() != null) {
                       helper.setUserToken(loginResultDTOResultResponse.getData().getTokenValue());
                   }
               }
           }
       });

    }

    public Observable<ResultResponse> logout() {
        return myService.logout(helper.getToken()).doOnNext(new Consumer<ResultResponse>() {
            @Override
            public void accept(ResultResponse resultResponse) throws Exception {
                helper.setUserToken("");
            }
        });
    }


    public  Observable<ResultResponse<UserBean>> getUserInfo() {
        return myService.getUserInfo(helper.getToken());
    }



}
