package com.pft.quicktouch.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.Sale;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 菜品分类适配
 */
public class ALlSaleAdapter extends BaseAdapter {

    private Context mContext;
    private List<Sale> list;


    public ALlSaleAdapter(Context context, List<Sale> list) {
        mContext = context;
        this.list = list;
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
        ViewHolder viewHolder = null;
        if (view == null) {

            view = LayoutInflater.from(mContext).inflate(R.layout.item_all_sale_detail_lv,
                    null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Sale sale = list.get(position);
        viewHolder.time.setText(sale.getStartTime() + " 至 " + sale.getEndTime());
        viewHolder.desc.setText(sale.getCount());

        return view;
    }


    static class ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.desc)
        TextView desc;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
