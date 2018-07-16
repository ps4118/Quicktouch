package com.pft.quicktouch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pft.quicktouch.R;
import com.pft.quicktouch.ui.adapter.MyFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class OrderFragment extends Fragment {
    View view;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;
    private List<Fragment> fragments;
    private List<String> tabList;
    private TodayOrderFragment mTodayOrderFragment;
    private HistoryOrderFragment mHistoryOrderFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_order, null);
        }
        unbinder = ButterKnife.bind(this, view);
        initview();
        return view;
    }

    /**
     * 开始初始化控件，并封装两个fragment切换
     */
    private void initview() {
        fragments = new ArrayList<>();
        mTodayOrderFragment = TodayOrderFragment.newInstance();
        mHistoryOrderFragment = HistoryOrderFragment.newInstance();
        fragments.add(mTodayOrderFragment);
        fragments.add(mHistoryOrderFragment);
        tabList = new ArrayList<>();
        tabList.add("今日订单");
        tabList.add("历史订单");
        tabLayout.addTab(tabLayout.newTab().setText("今日订单"));
        tabLayout.addTab(tabLayout.newTab().setText("历史订单"));
        tabLayout.setupWithViewPager(vp);
        FragmentManager manager = getChildFragmentManager();
        MyFragmentAdapter adapter = new MyFragmentAdapter(manager, fragments, tabList);
        vp.setAdapter(adapter);
        tabLayout.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //tab被选的时候回调
                vp.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //tab未被选择的时候回调
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //tab重新选择的时候回调
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
