package com.example.hiotmvp.ui.login;

import android.text.TextUtils;

import com.example.hiotmvp.data.DataManager;
import com.example.hiotmvp.data.bean.LoginResultDTO;
import com.example.hiotmvp.data.bean.ResultResponse;
import com.example.hiotmvp.ui.base.BasePresenter;
import com.example.hiotmvp.utils.Constants;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginView> {

    @Inject
    DataManager dataManager;

    @Inject
    public LoginPresenter() {
    }

    public void login(String userName, String password) {
        subscribe(dataManager.login(userName, password), new CallBackResonse<ResultResponse<LoginResultDTO>>() {
            @Override
            public void onNext(ResultResponse<LoginResultDTO> loginResultDTOResultResponse) {
                if (loginResultDTOResultResponse == null) {
                    baseView.showMessage("服务器正忙，请您稍后重试");
                    return;
                } else {
                    if (loginResultDTOResultResponse.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                        if (loginResultDTOResultResponse.getData() != null) {
                            baseView.loginSuccess();
                        }
                    } else {
                        if (!TextUtils.isEmpty(loginResultDTOResultResponse.getMsg())) {
                            baseView.showMessage(loginResultDTOResultResponse.getMsg());
                        }
                    }


                }
            }
        });

    }
}
