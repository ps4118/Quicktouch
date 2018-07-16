package com.pft.quicktouch.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.Tixian;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 提现流水适配器
 */
public class TixianAdapter extends BaseAdapter {
    List<Tixian> mList;
    Context mContext;

    public TixianAdapter(Context context, List<Tixian> list) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
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

            view = LayoutInflater.from(mContext).inflate(R.layout.item_tixian_lv, null);
            holder = new ViewHolder(view);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        Tixian tx = mList.get(position);
        holder.time.setText(tx.getTime());
        holder.tixianMoney.setText(tx.getTxMoney());
        holder.shouxuMoney.setText(tx.getSxMoney());
        holder.daozhangMoney.setText(tx.getDzMoney());

        return view;
    }

    class ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.tixian_money)
        TextView tixianMoney;
        @BindView(R.id.shouxu_money)
        TextView shouxuMoney;
        @BindView(R.id.daozhang_money)
        TextView daozhangMoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
