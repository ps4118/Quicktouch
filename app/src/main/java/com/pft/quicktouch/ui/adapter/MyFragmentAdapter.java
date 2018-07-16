package com.pft.quicktouch.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 主页面fragment切换适配器
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    private List<String> list;
    private List<Fragment> mFragments;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> mFragments, List<String> list) {
        super(fm);
        this.list = list;
        this.mFragments = mFragments;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
