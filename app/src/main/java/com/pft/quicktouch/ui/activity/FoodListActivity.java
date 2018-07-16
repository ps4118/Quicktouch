package com.pft.quicktouch.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.Food;
import com.pft.quicktouch.mvp.contract.FoodManagerContract;
import com.pft.quicktouch.mvp.presenter.FoodManagerPresenter;
import com.pft.quicktouch.ui.adapter.FoodManagerAdapter;
import com.pft.quicktouch.view.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 负责选择菜品的页面
 */
public class FoodListActivity extends BaseActivity<FoodManagerContract.FoodManagerView, FoodManagerPresenter> implements
        TitleBar.TitleBarClickListener, FoodManagerContract.FoodManagerView {

    List<Food> list = new ArrayList<Food>();
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.typeSpanner)
    Spinner typeSpanner;
    @BindView(R.id.statusSpanner)
    Spinner statusSpanner;
    @BindView(R.id.listview)
    PullToRefreshListView listview;
    FoodManagerAdapter mAdapter;

    @Override
    protected FoodManagerPresenter createPresenter() {
        return new FoodManagerPresenter();
    }

    @Override
    protected void initview() {
        titlebar.setTopBarClickListener(this);
        titlebar.setTitle("菜品");
        mAdapter = new FoodManagerAdapter(FoodListActivity.this, list, null, 2);
        listview.setAdapter(mAdapter);
        //设置下拉刷新，上拉加载
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        //设置图标
        listview.getLoadingLayoutProxy(true, true).setLoadingDrawable(getResources().getDrawable(R.drawable.refresh_icon));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food food = list.get(position - 1);
                Intent intent = new Intent();
                intent.putExtra("food", food);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void initdata() {
        for (int i = 1; i < 10; i++) {
            Food food = new Food();
            food.setName("菜品" + i);
            food.setPrice(i * 9 + "");
            list.add(food);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_food_list;
    }

    @Override
    public void getList(String msg) {

    }

    @Override
    public void noMoreData() {

    }

    @Override
    public void updateSuccess(String msg) {

    }

    @Override
    public void updateError(String msg) {

    }

    @Override
    public void onstart() {

    }

    @Override
    public void onfinish() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void leftClick() {
        finish();
        list.clear();
        list = null;
    }

    @Override
    public void rightClick() {

    }
}
