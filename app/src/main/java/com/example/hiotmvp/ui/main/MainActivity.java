package com.example.hiotmvp.ui.main;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.hiotmvp.R;
import com.example.hiotmvp.ui.base.BaseActivity;
import com.example.hiotmvp.ui.mine.MineFragment;
import com.example.hiotmvp.widget.NoSlideViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {


    @BindView(R.id.noslide_viewpager)
    NoSlideViewPager noSlideViewPager;

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        noSlideViewPager.setSlide(false);
        noSlideViewPager.setAdapter(new FragementAdapter(getSupportFragmentManager()));
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_message:
                        noSlideViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_equipment:
                        noSlideViewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_scene:
                        noSlideViewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_mine:
                        noSlideViewPager.setCurrentItem(3);
                        break;
                }

            }
        });
    }

    class FragementAdapter extends FragmentPagerAdapter{
        List<Fragment> fragments = new ArrayList<>();

        public FragementAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(new MessageFragment());
            fragments.add(new EquipmentFragment());
            fragments.add(new SceneFragment());
            fragments.add(new MineFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    public void injectDependence() {

    }

    @Override
    public MainPresenter createPresenter() {
        return null;
    }


    @Override
    public void showMessage(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
