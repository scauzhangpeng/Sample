package com.xyz.sample.lifecycle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ZP on 2018/2/8.
 * <p>
 * 首次创建fragment，setUserVisible不管true还是false都前于生命周期
 * </p>
 */

public class LifeCyclePageAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public LifeCyclePageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
