package com.example.hiotmvp.ui.main;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hiotmvp.R;
import com.example.hiotmvp.ui.base.BaseFragment;
import com.example.hiotmvp.ui.base.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SceneFragment extends BaseFragment {
    public SceneFragment() {
        // Required empty public constructor
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scene, container, false);
    }

    @Override
    public void injectDependence() {

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void showMessage(String msg) {

    }
}
