package com.example.hiotmvp.ui.login;


import com.example.hiotmvp.data.DataManager;
import com.example.hiotmvp.data.bean.ResultResponse;
import com.example.hiotmvp.data.bean.User;
import com.example.hiotmvp.ui.base.BasePresenter;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    DataManager dataManager;

    @Inject
    public MainPresenter() {

    }


    public void fetch() {
        subscribe(dataManager.getUser(), new CallBackResonse<ResultResponse<User>>() {
            @Override
            public void onNext(ResultResponse<User> userResultResponse) {

                if (!isDetach()) {
                    baseView.showMessage(userResultResponse.getStatus());
                    baseView.showMessage(userResultResponse.getData().getName());
                }
            }
        });

    }
}
