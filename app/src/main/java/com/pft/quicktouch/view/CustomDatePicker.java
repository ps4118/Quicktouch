package com.pft.quicktouch.view;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.DatePicker;

import com.pft.quicktouch.interfaces.DateChooseListener;

import java.util.Calendar;

public class CustomDatePicker extends DatePicker implements DatePickerDialog.OnClickListener {
    private DatePickerDialog mDatePickerDialog;


    public CustomDatePicker(Context context, DateChooseListener listener) {
        super(context);
        init(context, listener);
    }

    public CustomDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    public void init(Context context, final DateChooseListener listener) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        mDatePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                listener.dateChoose(year + "年" + (month + 1) + "月" + day + "日");
            }
        }, year, month, day);
        mDatePickerDialog.setCancelable(true);
        mDatePickerDialog.setCanceledOnTouchOutside(true);
        mDatePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //确定的逻辑代码在监听中实现
                        DatePicker picker = mDatePickerDialog.getDatePicker();
                        int year = picker.getYear();
                        int month = picker.getMonth();
                        int day = picker.getDayOfMonth();
                        listener.dateChoose(year + "年" + (month + 1) + "月" + day + "日");
                    }
                });
        mDatePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //取消什么也不用做
                        dis();

                    }
                });


    }

    public void dis() {
        if (mDatePickerDialog != null && mDatePickerDialog.isShowing()) {
            mDatePickerDialog.dismiss();
            mDatePickerDialog = null;
        }
    }

    public void show() {
        if (mDatePickerDialog != null) {
            mDatePickerDialog.show();

        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
