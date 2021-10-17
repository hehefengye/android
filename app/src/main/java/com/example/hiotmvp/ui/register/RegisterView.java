package com.example.hiotmvp.ui.register;

import com.example.hiotmvp.ui.base.BaseView;

public interface RegisterView extends BaseView {

    public void registerSuccess(String userName, String password);

    public void loginSuccess();

}
