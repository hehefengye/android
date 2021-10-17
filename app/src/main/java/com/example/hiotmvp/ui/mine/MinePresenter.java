package com.example.hiotmvp.ui.mine;

import com.example.hiotmvp.data.DataManager;
import com.example.hiotmvp.data.bean.ResultResponse;
import com.example.hiotmvp.data.bean.UserBean;
import com.example.hiotmvp.ui.base.BasePresenter;
import com.example.hiotmvp.utils.Constants;

import javax.inject.Inject;

public class MinePresenter extends BasePresenter<MineView> {
    @Inject
    DataManager dataManager;

    @Inject
    public MinePresenter() {
    }

    public void loadUserInfo() {
        subscribe(dataManager.getUserInfo(), new CallBackResonse<ResultResponse<UserBean>>() {
            @Override
            public void onNext(ResultResponse<UserBean> userBeanResultResponse) {
                if (userBeanResultResponse == null) {
                    baseView.showMessage("服务器开小差了，请稍后再试");
                    return;
                }
                //如果token失效
                if (userBeanResultResponse.getStatus() == Constants.MSG_STATUS_TOKEN_OUT) {
                    baseView.tokenOut();
                    return;
                }
                if (userBeanResultResponse.getStatus() != Constants.MSG_STATUS_SUCCESS) {
                    baseView.showMessage(userBeanResultResponse.getMsg());
                    return;
                }
                if (userBeanResultResponse.getData() == null) {
                    baseView.showMessage("服务器开小差了，请稍后再试");
                    return;
                }
                UserBean userBean = userBeanResultResponse.getData();
                baseView.refreshUserInfo(userBean);
            }
        });

    }

    public void logout() {
        subscribe(dataManager.logout(), new CallBackResonse<ResultResponse>() {
            @Override
            public void onNext(ResultResponse resultResponse) {
                if (resultResponse == null) {
                    baseView.showMessage("服务器开小差了，请稍后再试");
                    return;
                }
                //如果token失效
                if (resultResponse.getStatus() == Constants.MSG_STATUS_TOKEN_OUT) {
                    baseView.tokenOut();
                    return;
                }
                if (resultResponse.getStatus() != Constants.MSG_STATUS_SUCCESS) {
                    baseView.showMessage(resultResponse.getMsg());
                    return;
                }
                //如果返回成功，打开登录界面
                baseView.tokenOut();
            }
        });
    }


}
