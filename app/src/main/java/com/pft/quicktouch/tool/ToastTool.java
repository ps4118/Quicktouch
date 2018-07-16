package com.pft.quicktouch.tool;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast显示提示消息
 */
public class ToastTool {
    static Toast toast = null;

    public static void showToast(Context context, String msg) {
        if (toast == null) {
            if (toast == null) {
                toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
            }
        }
        toast.setText(msg);
        toast.show();
    }
}
