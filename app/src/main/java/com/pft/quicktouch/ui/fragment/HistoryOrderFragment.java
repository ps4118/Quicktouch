package com.pft.quicktouch.ui.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.Food;
import com.pft.quicktouch.bean.Order;
import com.pft.quicktouch.mvp.contract.HistoryOrderContract;
import com.pft.quicktouch.mvp.presenter.HistoryOrderPresenter;
import com.pft.quicktouch.ui.adapter.HistoryOrderAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HistoryOrderFragment extends BaseFragment<HistoryOrderContract.HistoryOrderView, HistoryOrderPresenter> implements HistoryOrderContract.HistoryOrderView {
    View view;
    List<Order> list = new ArrayList<>();

    HistoryOrderAdapter mAdapter;
    @BindView(R.id.orderTv)
    TextView orderTv;
    @BindView(R.id.orderCount)
    TextView orderCount;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.spanner)
    Spinner spanner;
    @BindView(R.id.listview)
    PullToRefreshListView listview;
    Unbinder unbinder;
    View topView;

    @Override
    protected HistoryOrderPresenter createPresenter() {
        return new HistoryOrderPresenter();
    }

    @Override
    public void initview() {
        mAdapter = new HistoryOrderAdapter(getContext(), list);
        listview.setAdapter(mAdapter);
        //设置下拉刷新，上拉加载
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        //设置图标
        listview.getLoadingLayoutProxy(true, true).setLoadingDrawable(getActivity().getResources().getDrawable(R.drawable.refresh_icon));

        //设置空数据视图
        TextView view = new TextView(getContext());
        view.setText("暂无数据");
        view.setGravity(Gravity.CENTER);
        listview.setEmptyView(view);

        spanner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] str = getContext().getResources().getStringArray(R.array.order_spanner);
                orderTv.setText(str[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void initdata() {
        for (int i = 0; i < 5; i++) {
            Order order = new Order();
            order.setDaytime("2018-7-17");
            order.setTotal(100 + "");
            List<Food> foodlist = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Food food = new Food();
                food.setPrice("15");
                food.setName("鱼香肉丝鱼香肉丝鱼香肉丝鱼香肉丝鱼香肉丝鱼香肉丝");
                food.setOrderTime("12:00:00");
                food.setType("堂");
                foodlist.add(food);
            }
            order.setList(foodlist);
            list.add(order);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_history_order;
    }

    /**
     * 获取当前fragment对象
     *
     * @return
     */
    public static HistoryOrderFragment newInstance() {
        HistoryOrderFragment fragment = new HistoryOrderFragment();
        return fragment;
    }

    @Override
    public void getSuccess(String msg) {

    }

    @Override
    public void getError(String msg) {

    }

    @Override
    public void onstart() {

    }

    @Override
    public void onfinish() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
