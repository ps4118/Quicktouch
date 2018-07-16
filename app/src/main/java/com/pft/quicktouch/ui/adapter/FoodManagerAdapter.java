package com.pft.quicktouch.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.Food;
import com.pft.quicktouch.interfaces.FoodManagerItemListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页面菜品管理适配
 */
public class FoodManagerAdapter extends BaseAdapter {
    Context mContext;
    List<Food> list;
    private FoodManagerItemListener mListener;

    private int flag;
    public static final int Manage_food = 1;
    public static final int Choose_food = 2;


    /**
     * 构造方法
     *
     * @param context   上下文
     * @param list      数据源
     * @param mListener 监听接口
     * @param flag      判断是管理还是选择菜品，如果是选择菜品不显示三个操作按钮
     *                  1:管理菜品
     *                  2:选择菜品
     */
    public FoodManagerAdapter(Context context, List<Food> list, FoodManagerItemListener mListener, int flag) {
        mContext = context;
        this.mListener = mListener;
        this.list = list;
        this.flag = flag;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_food_manager,
                    null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Food food = list.get(position);
        viewHolder.foodname.setText(food.getName());
        viewHolder.price.setText(food.getPrice());
        viewHolder.sum.setText("总销量:" + food.getSum());
        viewHolder.todayCount.setText("今日销量:" + food.getTodayCount());
        viewHolder.foodImg.setImageResource(R.drawable.icon);
        if (flag == 1) {
            //管理菜品。显示操作栏
            viewHolder.handleLl.setVisibility(View.VISIBLE);
        } else {
            //选择菜品，隐藏操作栏
            viewHolder.handleLl.setVisibility(View.GONE);
        }
        switch (food.getStatus()) {
            case 0:
                //有货
                viewHolder.youhuo.setTextColor(Color.WHITE);
                viewHolder.youhuo.setBackgroundResource(R.drawable.shape_food_manager_select);
                viewHolder.shouqing.setTextColor(Color.BLACK);
                viewHolder.shouqing.setBackgroundResource(R.drawable.shape_food_manager_noselect);
                viewHolder.xiajia.setTextColor(Color.BLACK);
                viewHolder.xiajia.setBackgroundResource(R.drawable.shape_food_manager_noselect);
                break;
            case 1:
                //售罄
                viewHolder.youhuo.setTextColor(Color.BLACK);
                viewHolder.youhuo.setBackgroundResource(R.drawable.shape_food_manager_noselect);
                viewHolder.shouqing.setTextColor(Color.WHITE);
                viewHolder.shouqing.setBackgroundResource(R.drawable.shape_food_manager_select);
                viewHolder.xiajia.setTextColor(Color.BLACK);
                viewHolder.xiajia.setBackgroundResource(R.drawable.shape_food_manager_noselect);
                break;
            case 2:
                //下架
                viewHolder.youhuo.setTextColor(Color.BLACK);
                viewHolder.youhuo.setBackgroundResource(R.drawable.shape_food_manager_noselect);
                viewHolder.shouqing.setTextColor(Color.BLACK);
                viewHolder.shouqing.setBackgroundResource(R.drawable.shape_food_manager_noselect);
                viewHolder.xiajia.setTextColor(Color.WHITE);
                viewHolder.xiajia.setBackgroundResource(R.drawable.shape_food_manager_select);
                break;


        }
        //售罄点击
        viewHolder.shouqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.shouqing(position);
            }
        });
        //有货点击
        viewHolder.youhuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.youhuo(position);
            }
        });
        //下架点击
        viewHolder.xiajia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.youhuo(position);
            }
        });

        return convertView;
    }


    class ViewHolder {
        @BindView(R.id.foodImg)
        ImageView foodImg;
        @BindView(R.id.foodname)
        TextView foodname;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.todayCount)
        TextView todayCount;
        @BindView(R.id.sum)
        TextView sum;
        @BindView(R.id.youhuo)
        TextView youhuo;
        @BindView(R.id.shouqing)
        TextView shouqing;
        @BindView(R.id.xiajia)
        TextView xiajia;
        @BindView(R.id.handle_ll)
        LinearLayout handleLl;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
