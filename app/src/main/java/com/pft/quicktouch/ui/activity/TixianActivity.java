package com.pft.quicktouch.ui.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.Tixian;
import com.pft.quicktouch.mvp.contract.TixianContract;
import com.pft.quicktouch.mvp.presenter.TixianPresenter;
import com.pft.quicktouch.ui.adapter.TixianAdapter;
import com.pft.quicktouch.view.TitleBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现流水页面
 */
public class TixianActivity extends BaseActivity<TixianContract.TixianView, TixianPresenter> implements TitleBar.TitleBarClickListener, TixianContract.TixianView {

    List<Tixian> list = new ArrayList<>();
    TixianAdapter mAdapter;
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.before_year_img)
    ImageView beforeYearImg;
    @BindView(R.id.yearTv)
    TextView yearTv;
    @BindView(R.id.after_year_img)
    ImageView afterYearImg;
    @BindView(R.id.before_month_img)
    ImageView beforeMonthImg;
    @BindView(R.id.monthTv)
    TextView monthTv;
    @BindView(R.id.after_month_img)
    ImageView afterMonthImg;
    @BindView(R.id.txLv)
    PullToRefreshListView listview;

    //设置年月
    int year, month;
    Calendar calendar;

    @Override
    protected TixianPresenter createPresenter() {
        return new TixianPresenter();
    }

    @Override
    protected void initview() {
        //默认显示当前年月
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);

        yearTv.setText(year + "年");
        monthTv.setText(month + "月");


        titlebar.setTitle("提现流水");
        titlebar.setTopBarClickListener(this);
        mAdapter = new TixianAdapter(TixianActivity.this, list);
        listview.setAdapter(mAdapter);
        //设置下拉刷新，上拉加载
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        //设置图标
        listview.getLoadingLayoutProxy(true, true).setLoadingDrawable(getResources().getDrawable(R.drawable.refresh_icon));

        //设置空数据视图
        TextView view = new TextView(TixianActivity.this);
        view.setText("暂无数据");
        view.setGravity(Gravity.CENTER);
        listview.setEmptyView(view);
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                listview.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                listview.onRefreshComplete();
            }
        });


        //获取数据(默认当年当月)
        String url = "";
        mPresenter.getTx(url);

    }

    @Override
    protected void initdata() {
        for (int i = 1; i < 10; i++) {
            Tixian tx = new Tixian();
            tx.setTime("2018-7-16");
            tx.setTxMoney(i * 100 + "");
            tx.setSxMoney(i * 5 + "");
            tx.setDzMoney(i * 95 + "");
            list.add(tx);
        }
    }

    //减年
    @OnClick(R.id.before_year_img)
    public void reduceYear() {
        int now_month = calendar.get(Calendar.MONTH);//

        year--;
        yearTv.setText(year + "年");
    }


    //加年
    @OnClick(R.id.after_year_img)
    public void addYear() {
        //获取当前年，与操作前的年比较
        int now_year = calendar.get(Calendar.YEAR);//
        //当前年前，就加1。否则不做改变
        if (year < now_year) {
            year++;
        }
        yearTv.setText(year + "年");
    }

    //加月
    @OnClick(R.id.after_month_img)
    public void addMonth() {
        month++;
        if (month > 12) {
            month = 1;
        }
        monthTv.setText(month + "月");
    }

    //减月
    @OnClick(R.id.before_month_img)
    public void reduceMonth() {
        month--;
        if (month < 1) {
            month = 12;
        }
        monthTv.setText(month + "月");


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tixian;
    }

    @Override
    public void txSuccess(String msg) {

    }

    @Override
    public void txError(String msg) {

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

    }


}
