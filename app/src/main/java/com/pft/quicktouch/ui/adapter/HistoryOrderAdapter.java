package com.pft.quicktouch.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.Food;
import com.pft.quicktouch.bean.Order;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 历史订单适配器
 */
public class HistoryOrderAdapter extends BaseAdapter {
    List<Order> list;
    Context mContext;

    public HistoryOrderAdapter(Context context, List<Order> list) {
        this.list = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        if (list.size() == 0 || list == null) {
            return 0;
        }

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_history_order_lv, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Order order = list.get(position);
        holder.daytime.setText(order.getDaytime());
        holder.total.setText(order.getTotal());
        List<Food> foodlist = order.getList();
        HistoryOrderFoodAdapter adapter = new HistoryOrderFoodAdapter(mContext, foodlist);
        holder.lv.setAdapter(adapter);

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.daytime)
        TextView daytime;
        @BindView(R.id.total)
        TextView total;
        @BindView(R.id.lv)
        ListView lv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
