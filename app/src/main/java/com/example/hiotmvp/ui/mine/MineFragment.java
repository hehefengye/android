package com.example.hiotmvp.ui.mine;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hiotmvp.R;
import com.example.hiotmvp.data.bean.UserBean;
import com.example.hiotmvp.ui.base.BaseActivity;
import com.example.hiotmvp.ui.base.BaseFragment;
import com.example.hiotmvp.ui.login.LoginActivity;
import com.example.hiotmvp.utils.ImageUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView {

    @BindView(R.id.iv_head_image)
    ImageView ivHeadImage;

    @BindView(R.id.tv_user_center_nickname)
    TextView tvUserCenterNickname;

    @BindView(R.id.tv_user_center_email)
    TextView tvUserCenterEmail;

    @BindView(R.id.tv_user_center_update_password)
    TextView tvUserCenterUpdatePassword;

    @BindView(R.id.tv_user_center_update_email)
    TextView tvUserCenterUpdateEmail;

    @BindView(R.id.btn_logout)
    Button btnLogout;

    @Inject
    MinePresenter minePresenter;

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        minePresenter.loadUserInfo();
    }

    @Override
    public void injectDependence() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity)getActivity()).getActivityComponent().inject(this);
        }
    }

    @Override
    public MinePresenter createPresenter() {
        return minePresenter;
    }


    @Override
    public void showMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_logout:
                minePresenter.logout();
                break;
        }

    }


    @Override
    public void tokenOut() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void refreshUserInfo(UserBean userBean) {
        ImageUtils.showCircle(getActivity(), ivHeadImage, ImageUtils.getFullUrl(userBean.getImg()));
        tvUserCenterEmail.setText(userBean.getEmail());
        tvUserCenterNickname.setText(userBean.getUsername());
    }
}
