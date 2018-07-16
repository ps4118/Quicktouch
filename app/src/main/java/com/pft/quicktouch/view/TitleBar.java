package com.pft.quicktouch.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pft.quicktouch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 标题栏
 */
@SuppressLint("NewApi")
public class TitleBar extends RelativeLayout {
    @BindView(R.id.left_tv)
    TextView leftTv;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.right_img)
    ImageView rightImg;

    private View title_view;

    private TitleBarClickListener titleBarClickListener;
    String titlename, rightStr, leftStr;

    public void showRightImg(boolean show) {
        if (!show) {
            rightImg.setVisibility(View.INVISIBLE);
        } else {
            rightImg.setVisibility(View.VISIBLE);
        }
    }

    public void showRightTv(boolean show) {
        if (!show) {
            rightTv.setVisibility(View.INVISIBLE);
        } else {
            rightTv.setVisibility(View.VISIBLE);
        }
    }

    public void showTitle(boolean show) {
        if (!show) {
            title.setVisibility(View.INVISIBLE);
        } else {
            title.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 设置右文字
     *
     * @param str
     */
    public void setRightTv(String str) {
        rightTv.setText(str);

    }

    public String getTitle_text() {
        return titlename;
    }

    public void showLeft(boolean show) {

        if (!show) {
            leftTv.setVisibility(View.INVISIBLE);
        } else {
            leftTv.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 设置标题栏背景颜色
     *
     * @param color
     */
    public void setBackgroundColor(String color) {
        setBackgroundColor(Color.parseColor(color));
    }

    int bg;

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        title_view = inflate(context, R.layout.title_layout, null);
        ButterKnife.bind(this, title_view);
        title_view.setBackgroundColor(bg);
        addView(title_view);
        leftTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleBarClickListener != null) {
                    titleBarClickListener.leftClick();
                }
            }
        });

        rightTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleBarClickListener != null) {
                    titleBarClickListener.rightClick();
                }
            }
        });
        rightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleBarClickListener != null) {
                    titleBarClickListener.rightClick();
                }
            }
        });
    }

    /**
     * 注册接口，实现接口方法
     *
     * @param titleBarClickListener
     */
    public void setTopBarClickListener(
            TitleBarClickListener titleBarClickListener) {
        this.titleBarClickListener = titleBarClickListener;
    }


    public void setTitle(String titlestr) {
        this.titlename = titlestr;
        title.setText(titlename);
    }


    /**
     * 接口定义不同的方法
     *
     * @author chenyong
     */
    public interface TitleBarClickListener {
        /**
         * 标题栏左侧按钮
         */
        void leftClick();

        /**
         * 标题栏右侧按钮
         */
        void rightClick();
    }
}
