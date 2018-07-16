package com.pft.quicktouch.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.FoodType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 菜品分类适配
 */
public class FoodTypeAdapter extends BaseAdapter {

    private Context mContext;
    private List<FoodType> list;
    //默认选中下标0
    public int selectPosition = 0;

    /**
     * 设置选中的下标
     *

     */

    public void setSelectPosition(int position) {
        selectPosition = position;
    }

    public FoodTypeAdapter(Context context, List<FoodType> list) {
        mContext = context;
        this.list = list;
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {

            view = LayoutInflater.from(mContext).inflate(R.layout.item_food_update_type,
                    null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        FoodType type = list.get(position);
        viewHolder.name.setText(type.getName());
        if (selectPosition == position) {
            //选中就更改背景色
            viewHolder.name.setBackgroundColor(mContext.getResources().getColor(R.color.titilebar_blue));
            viewHolder.name.setTextColor(Color.WHITE);
        } else {
            viewHolder.name.setBackgroundColor(Color.WHITE);
            viewHolder.name.setTextColor(Color.BLACK);

        }
        return view;
    }


    class ViewHolder {
        @BindView(R.id.name)
        TextView name;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
