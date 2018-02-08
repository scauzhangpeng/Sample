package com.xyz.sample.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.xyz.sample.BaseActivity;
import com.xyz.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZP on 2018/2/8.
 */

public class LifeCycleActivity extends BaseActivity {


    private ViewPager vp;
    private List<Fragment> mFragments;
    private LifeCyclePageAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_lift_cycle);
        initView();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        mFragments = new ArrayList<>();
        mFragments.add(new FirstFragment());
        mFragments.add(new SecondFragment());
        mFragments.add(new ThirdFragment());
        mFragments.add(new FourthFragment());
        mAdapter = new LifeCyclePageAdapter(getSupportFragmentManager(), mFragments);
        vp.setAdapter(mAdapter);
    }
}
