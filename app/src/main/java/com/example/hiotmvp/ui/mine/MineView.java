package com.example.hiotmvp.ui.mine;

import com.example.hiotmvp.data.bean.UserBean;
import com.example.hiotmvp.ui.base.BaseView;

public interface MineView extends BaseView {

    /**
     * 重新登录的处理
     */
    void tokenOut();

    void refreshUserInfo(UserBean userBean);
}
