package com.pft.quicktouch.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.Food;
import com.pft.quicktouch.bean.Sale;
import com.pft.quicktouch.mvp.contract.SaleDetailsContract;
import com.pft.quicktouch.mvp.presenter.SaleDetailsPresenter;
import com.pft.quicktouch.ui.adapter.ALlSaleAdapter;
import com.pft.quicktouch.ui.adapter.FoodSaleAdapter;
import com.pft.quicktouch.view.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SaleDetailsActivity extends BaseActivity<SaleDetailsContract.SaleDetailsView, SaleDetailsPresenter> implements TitleBar.TitleBarClickListener, SaleDetailsContract.SaleDetailsView {


    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.logo)
    CircleImageView logo;
    @BindView(R.id.store)
    TextView store;


    List<Sale> mSaleList = new ArrayList<>();
    List<Food> foodlist = new ArrayList<>();


    ALlSaleAdapter mALlSaleAdapter;
    FoodSaleAdapter mFoodSaleAdapter;
    String action = "all";//区分全场折扣还是特价菜品折扣，显示不同的列表数据


    @BindView(R.id.expandListview)
    PullToRefreshExpandableListView expandListview;//特价菜二级列表
    @BindView(R.id.listview)
    PullToRefreshListView listview;

    @Override
    protected SaleDetailsPresenter createPresenter() {
        return new SaleDetailsPresenter();
    }

    @Override
    protected void initview() {
        action = getIntent().getStringExtra("action");
        titlebar.setRightTv("添加折扣");
        titlebar.setTopBarClickListener(this);
        //设置空数据视图
        TextView view = new TextView(SaleDetailsActivity.this);
        view.setText("暂无数据");
        view.setGravity(Gravity.CENTER);

        if (action.equals("all")) {
            initListview();
            //显示全场折扣列表
            expandListview.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
            titlebar.setTitle("全场折扣");
            mALlSaleAdapter = new ALlSaleAdapter(SaleDetailsActivity.this, mSaleList);
            listview.setAdapter(mALlSaleAdapter);
            mALlSaleAdapter.notifyDataSetChanged();
            expandListview.setEmptyView(view);
        } else {
            //显示特价菜品列表
            expandListview.setVisibility(View.VISIBLE);
            listview.setVisibility(View.GONE);
            titlebar.setTitle("特价菜折扣");
            mFoodSaleAdapter = new FoodSaleAdapter(SaleDetailsActivity.this, mSaleList, foodlist);
            expandListview.getRefreshableView().setAdapter(mFoodSaleAdapter);

            initexpandListview();
        }


    }

    void initListview() {
        //设置下拉刷新，上拉加载
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        //设置图标
        listview.getLoadingLayoutProxy(true, true).setLoadingDrawable(getResources().getDrawable(R.drawable.refresh_icon));


    }

    void initexpandListview() {
        //设置下拉刷新，上拉加载
        expandListview.setMode(PullToRefreshBase.Mode.BOTH);
        //设置图标
        expandListview.getLoadingLayoutProxy(true, true).setLoadingDrawable(getResources().getDrawable(R.drawable.refresh_icon));

//      设置分组单击监听事件
        expandListview.getRefreshableView().setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                boolean groupExpanded = parent.isGroupExpanded(groupPosition);
                if (groupExpanded) {
                    parent.collapseGroup(groupPosition);
                } else {
                    parent.expandGroup(groupPosition, true);
                }
                return true;
            }
        });
        //隐藏默认的箭头
        expandListview.getRefreshableView().setGroupIndicator(null);
        //设置箭头样式
//        expandListview.getRefreshableView().setGroupIndicator(getResources().getDrawable(R.drawable.select_expand_tejia));
        //默认展开第一项
        expandListview.getRefreshableView().expandGroup(0);


    }

    @Override
    protected void initdata() {
        for (int i = 1; i < 5; i++) {
            Sale sale = new Sale();
            sale.setStartTime("今天");
            sale.setEndTime("明天");
            sale.setCount(i + "折");
            mSaleList.add(sale);
        }

        for (int i = 0; i < 3; i++) {
            Food food = new Food();
            food.setName("肉肉");
            food.setOldprice("9.99");
            food.setSaleprice("8.88");
            foodlist.add(food);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_sale_details;
    }


    @Override
    public void onstart() {

    }

    @Override
    public void onfinish() {

    }


    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        if (action.equals("all")) {
            Intent intent = new Intent(SaleDetailsActivity.this, AllDiscountActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SaleDetailsActivity.this, AddSaleFoodActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getAllSaleSuccess(String msg) {

    }

    @Override
    public void getAllSaleError(String msg) {

    }

    @Override
    public void getFoodSaleSuccess(String msg) {

    }

    @Override
    public void getFoodSaleError(String msg) {

    }
}
