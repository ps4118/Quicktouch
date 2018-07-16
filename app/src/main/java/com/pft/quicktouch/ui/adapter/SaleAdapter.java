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
import com.pft.quicktouch.bean.Sale;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 菜品分类适配
 */
public class SaleAdapter extends BaseAdapter {

    private Context mContext;
    private List<Sale> list;


    public SaleAdapter(Context context, List<Sale> list) {
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

            view = LayoutInflater.from(mContext).inflate(R.layout.item_main_sale_lv,
                    null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Sale sale = list.get(position);
        viewHolder.title.setText(sale.getTitle());
        viewHolder.time.setText(sale.getTime());
        viewHolder.count.setText(sale.getCount());
        viewHolder.img.setImageResource(R.drawable.icon);

        return view;
    }


    class ViewHolder {
        @BindView(R.id.img)
        CircleImageView img;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.time)
        TextView time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
