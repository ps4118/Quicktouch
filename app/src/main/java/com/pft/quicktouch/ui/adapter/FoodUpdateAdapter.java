package com.pft.quicktouch.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.Food;
import com.pft.quicktouch.bean.FoodType;
import com.pft.quicktouch.interfaces.FoodUpdateItemListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 菜品编辑适配
 */
public class FoodUpdateAdapter extends BaseAdapter {


    private Context mContext;
    private List<Food> list;
    FoodUpdateItemListener mListener;

    public FoodUpdateAdapter(Context context, List<Food> list, FoodUpdateItemListener listener) {
        mContext = context;
        this.list = list;
        this.mListener = listener;
    }

    @Override
    public int getCount() {
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
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {

            view = LayoutInflater.from(mContext).inflate(R.layout.item_food_lv,
                    null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Food food = list.get(position);
        viewHolder.foodname.setText(food.getName());
        viewHolder.foodprice.setText(food.getPrice());
        viewHolder.imageView.setImageResource(R.drawable.icon);
        //删除的点击事件
        viewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.del(position);
            }
        });
        //编辑的点击事件
        viewHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.update(position);
            }
        });
        //加入特价的点击事件
        viewHolder.sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.addSale(position);
            }
        });
        return view;
    }


    class ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.foodname)
        TextView foodname;
        @BindView(R.id.foodprice)
        TextView foodprice;
        @BindView(R.id.del)
        TextView del;
        @BindView(R.id.update)
        TextView update;
        @BindView(R.id.sale)
        TextView sale;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
