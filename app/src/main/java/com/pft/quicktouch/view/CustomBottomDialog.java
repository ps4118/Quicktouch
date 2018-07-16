package com.pft.quicktouch.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pft.quicktouch.R;
import com.pft.quicktouch.interfaces.BottomDialogListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 底部弹窗
 */
public class CustomBottomDialog extends BottomSheetDialog {
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.handleLv)
    ListView handleLv;
    private BottomSheetDialog mDialog;
    private View view;
    private Context mContext;
    private BottomDialogListener mListener;//点击监听
    private String[] str;//数据源

    public CustomBottomDialog(@NonNull Context context, BottomDialogListener listener) {
        super(context);
        mContext = context;
        mListener = listener;
        init();
    }


    /**
     * 初始化控件
     */
    public void init() {
        mDialog = new BottomSheetDialog(mContext);
        view = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_view, null);
        mDialog.setContentView(view);
        ButterKnife.bind(this, view);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        //构造适配器
        str = handleLv.getResources().getStringArray(R.array.food_handle);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, R.layout.item_bottom_sheet_dialog_lv, R.id.handleTv, str);
        //设置适配器
        handleLv.setAdapter(adapter);
        /**
         *listview列表的点击事件
         */
        handleLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //传递对应的点击数据
                mListener.handle(str[position]);
                disDialog();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancle();
            }
        });

    }

    /**
     * 显示
     */

    public void showDialog() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    /**
     * 消失
     */
    public void disDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    @OnClick(R.id.cancel)
    public void onViewClicked() {
    }
}
