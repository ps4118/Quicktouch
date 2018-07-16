package com.pft.quicktouch.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.pft.quicktouch.interfaces.DialogListener;

public class CustomAlertDialog extends AlertDialog {
    AlertDialog.Builder mDialog;

    public CustomAlertDialog(Context context) {
        super(context);
        mDialog = new AlertDialog.Builder(context);
    }


    /**
     * 构造弹窗
     *
     * @param title   标题
     * @param content 显示内容
     */
    public void init(String title, String content, final DialogListener listener) {

        mDialog.setCancelable(false);
        mDialog.setTitle(title);
        mDialog.setMessage(content);
        mDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.comfirm();
                    }
                });

        mDialog.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mDialog = null;
            }
        });
    }

    /**
     * 显示弹窗
     */
    public void show() {
        if (mDialog != null) {
            mDialog.show();
        }
    }
}
