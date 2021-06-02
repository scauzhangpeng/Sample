package com.xyz.sample.lifecycle;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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
