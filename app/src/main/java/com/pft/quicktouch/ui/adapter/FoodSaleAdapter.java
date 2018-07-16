package com.pft.quicktouch.ui.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Paint;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.Food;
import com.pft.quicktouch.bean.Sale;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 特价菜活动适配器
 */
public class FoodSaleAdapter implements ExpandableListAdapter {
    List<Sale> groupList;
    List<Food> foodList;
    Context mContext;


    public FoodSaleAdapter(Context context, List<Sale> groupList, List<Food> foodList) {
        this.groupList = groupList;
        this.foodList = foodList;
        mContext = context;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return foodList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        GroupHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_tejia_sale_group, null);
            holder = new GroupHolder(view);
            view.setTag(holder);
        } else {
            holder = (GroupHolder) view.getTag();
        }
        Sale sale = groupList.get(groupPosition);
        holder.groupTime.setText(sale.getStartTime() + " 至" + sale.getEndTime());


        if (isExpanded) {
            // 条目展开，设置向下的箭头
            holder.groupImg.setImageDrawable(mContext.getResources()
                    .getDrawable(R.drawable.ic_arrow_down));
        } else {
            // 条目未展开，设置向右的箭头
            holder.groupImg.setImageDrawable(mContext.getResources()
                    .getDrawable(R.drawable.ic_arrow_right));
        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        ChildHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_tejia_sale_child, null);
            holder = new ChildHolder(view);
            view.setTag(holder);
        } else {
            holder = (ChildHolder) view.getTag();
        }
        Food food = foodList.get(childPosition);
        holder.name.setText(food.getName());
        holder.oldprice.setText("原价" + food.getOldprice());
        holder.saleprice.setText("折后价" + food.getSaleprice());
        //原价添加斜线
        holder.oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }


    class GroupHolder {
        @BindView(R.id.group_time)
        TextView groupTime;
        @BindView(R.id.group_img)
        ImageView groupImg;

        GroupHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class ChildHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.oldprice)
        TextView oldprice;
        @BindView(R.id.saleprice)
        TextView saleprice;

        ChildHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
