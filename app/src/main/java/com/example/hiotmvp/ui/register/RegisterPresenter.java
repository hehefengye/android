package com.example.hiotmvp.ui.register;

import android.text.TextUtils;
import android.util.Log;

import com.example.hiotmvp.data.DataManager;
import com.example.hiotmvp.data.bean.LoginResultDTO;
import com.example.hiotmvp.data.bean.ResultResponse;
import com.example.hiotmvp.data.bean.UserBean;
import com.example.hiotmvp.ui.base.BasePresenter;
import com.example.hiotmvp.utils.Constants;

import javax.inject.Inject;

public class RegisterPresenter extends BasePresenter<RegisterView> {

    @Inject
    DataManager dataManager;

    @Inject
    public RegisterPresenter() {
    }

    public void register(String userName, String password, String email) {
        subscribe(dataManager.register(userName, password, email), new CallBackResonse<ResultResponse<UserBean>>() {
            @Override
            public void onNext(ResultResponse<UserBean> userBeanResultResponse) {
                if (userBeanResultResponse.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                    //如果注册成功，吐司注册成功
                    if (userBeanResultResponse != null && userBeanResultResponse.getData() != null) {
                        //弹出登录成功
                        baseView.showMessage("注册成功");

                        //自动登录，跳转到主界面
                        baseView.registerSuccess(userBeanResultResponse.getData().getUsername(), password);
                    }
                } else {
                    //如果注册失败，吐司服务端返回的错误信息
                    if (userBeanResultResponse != null && !TextUtils.isEmpty(userBeanResultResponse.getMsg())) {
                        baseView.showMessage(userBeanResultResponse.getMsg());
                    }
                }
            }
            @Override
            public void onError(Throwable e) {
                Log.e("RegisterPresenter", "onError", e);
                baseView.showMessage("网络拥堵中，请您稍后重试");
            }
        });


    }

    public void login(String userName, String password) {
        subscribe(dataManager.login(userName, password), new CallBackResonse<ResultResponse<LoginResultDTO>>() {
            @Override
            public void onNext(ResultResponse<LoginResultDTO> loginResultDTOResultResponse) {
                if (loginResultDTOResultResponse.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                    //如果登录身份正确
                    if (loginResultDTOResultResponse != null && loginResultDTOResultResponse.getData() != null) {
                        //弹出登录成功
//                        getView().showMessage("登录成功");

                        //跳转到主界面
                       baseView.loginSuccess();
                    }
                } else {
                    //如果登录身份错误，弹出服务端返回的错误信息
                    if (loginResultDTOResultResponse != null && !TextUtils.isEmpty(loginResultDTOResultResponse.getMsg())) {
                        baseView.showMessage(loginResultDTOResultResponse.getMsg());
                    }
                }

            }
        });
    }
}
