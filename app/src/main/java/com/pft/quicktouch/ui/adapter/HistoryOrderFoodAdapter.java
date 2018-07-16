package com.pft.quicktouch.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.Food;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 历史订单某天的菜品适配器
 */
public class HistoryOrderFoodAdapter extends BaseAdapter {
    List<Food> list;
    Context mContext;

    public HistoryOrderFoodAdapter(Context context, List<Food> list) {
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
            view = View.inflate(mContext, R.layout.item_history_order_food_lv, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Food food = list.get(position);
        holder.foodname.setText(food.getName());
        holder.ordermoney.setText(food.getPrice());
        holder.ordertime.setText(food.getOrderTime());
        holder.ordertype.setText(food.getType());


        return view;
    }

    class ViewHolder {
        @BindView(R.id.foodname)
        TextView foodname;
        @BindView(R.id.ordertime)
        TextView ordertime;
        @BindView(R.id.ordertype)
        TextView ordertype;
        @BindView(R.id.ordermoney)
        TextView ordermoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
