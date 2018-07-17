package com.pft.quicktouch.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.pft.quicktouch.R;

/**
 * 简单的空数据view，继承TextView
 */
public class CustomEmptyView extends LinearLayout {
    View view;

    public CustomEmptyView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        view = View.inflate(context, R.layout.view_empty, null);
        setGravity(Gravity.CENTER);
        addView(view);
    }
}
