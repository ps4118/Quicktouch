package com.pft.quicktouch.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.Sale;
import com.pft.quicktouch.mvp.contract.SaleContract;
import com.pft.quicktouch.mvp.presenter.SalePresenter;
import com.pft.quicktouch.ui.activity.SaleDetailsActivity;
import com.pft.quicktouch.ui.adapter.SaleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 促销活动
 */
public class SaleFragment extends BaseFragment<SaleContract.SaleView, SalePresenter> implements SaleContract.SaleView {

    @BindView(R.id.saleLv)
    ListView saleLv;
    //数据源
    List<Sale> mSaleList = new ArrayList<>();

    //适配器
    SaleAdapter mSaleAdapter;

    @Override

    protected SalePresenter createPresenter() {
        return new SalePresenter();
    }

    @Override
    public void initview() {
        mSaleAdapter = new SaleAdapter(getContext(), mSaleList);
        saleLv.setAdapter(mSaleAdapter);
        mSaleAdapter.notifyDataSetChanged();
        saleLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sale sale = mSaleList.get(position);
                switch (sale.getTitle()) {
                    case "全场折扣":
                        Intent intent = new Intent(getContext(), SaleDetailsActivity.class);
                        intent.putExtra("action", "all");
                        startActivity(intent);
                        break;
                    case "特价菜折扣":
                        Intent intent2 = new Intent(getContext(), SaleDetailsActivity.class);
                        intent2.putExtra("action", "tejia");
                        startActivity(intent2);
                        break;
                    case "代金券活动":
                        break;
                    case "特别加推菜品":
                        break;


                }
            }
        });
    }

    @Override
    public void initdata() {
        for (int i = 0; i < 2; i++) {
            Sale sale = new Sale();
            sale.setTitle("全场折扣");
            sale.setCount("倒送你钱");
            sale.setTime("有效期至：终生");
            mSaleList.add(sale);
        }
        Sale sale = new Sale();
        sale.setTitle("特价菜折扣");
        sale.setCount("倒送你钱");
        sale.setTime("有效期至：终生");
        mSaleList.add(sale);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sale;
    }

    @Override
    public void getSaleList(String msg) {

    }

    @Override
    public void getSaleError(String msg) {

    }

    @Override
    public void onstart() {

    }

    @Override
    public void onfinish() {

    }


}
