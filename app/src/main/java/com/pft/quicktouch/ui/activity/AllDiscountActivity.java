package com.pft.quicktouch.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pft.quicktouch.R;
import com.pft.quicktouch.interfaces.DateChooseListener;
import com.pft.quicktouch.mvp.contract.AllDiscoutContract;
import com.pft.quicktouch.mvp.presenter.AllDiscountPresenter;
import com.pft.quicktouch.tool.NetTool;
import com.pft.quicktouch.tool.ToastTool;
import com.pft.quicktouch.view.CustomDatePicker;
import com.pft.quicktouch.view.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加全场折扣
 */
public class AllDiscountActivity extends BaseActivity<AllDiscoutContract.AllDiscountView, AllDiscountPresenter> implements TitleBar.TitleBarClickListener, AllDiscoutContract.AllDiscountView {


    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.reduce)
    ImageView reduce;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.starttime)
    TextView starttime;
    @BindView(R.id.endtime)
    TextView endtime;


    String startStr = "", endStr = "";
    int dis = 100;
    @BindView(R.id.disTv)
    TextView disTv;
    @BindView(R.id.count)
    EditText count;

    @Override
    protected AllDiscountPresenter createPresenter() {
        return new AllDiscountPresenter();
    }

    @OnClick(R.id.add)
    public void addDis() {
        if (dis == 100) {
//            ToastTool.showToast(AllDiscountActivity.this, "折扣");
        } else {
            dis++;
            disTv.setText(dis + "");
        }
    }

    @OnClick(R.id.reduce)
    public void reduceDis() {
        if (dis == 1) {
//            ToastTool.showToast(AllDiscountActivity.this, "最低折扣不能低于1折");
        } else {
            dis--;
            disTv.setText(dis + "");
        }

    }

    @OnClick(R.id.starttime)
    public void setStart() {
        CustomDatePicker picker = new CustomDatePicker(AllDiscountActivity.this, new DateChooseListener() {
            @Override
            public void dateChoose(String time) {
                startStr = time;
                starttime.setText(startStr);
            }
        });
        picker.show();

    }

    @OnClick(R.id.endtime)
    public void setEnd() {
        CustomDatePicker picker = new CustomDatePicker(AllDiscountActivity.this, new DateChooseListener() {
            @Override
            public void dateChoose(String time) {
                endStr = time;
                endtime.setText(endStr);
            }
        });
        picker.show();
    }

    /**
     * 提交设置
     *
     * @param view
     */
    public void submit(View view) {
        if (NetTool.checkNetandWifi(AllDiscountActivity.this)) {
            String dis = count.getText().toString().trim();
            mPresenter.setDiscount(dis, startStr, endStr);
        } else {
            ToastTool.showToast(AllDiscountActivity.this, "请检查网络设置");
        }

    }

    @Override
    protected void initview() {
        titlebar.setTitle("全场折扣");
        titlebar.setTopBarClickListener(this);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_all_discount;
    }

    @Override
    public void setSuccess(String msg) {


    }

    @Override
    public void setError(String msg) {
        ToastTool.showToast(AllDiscountActivity.this, msg);
    }

    @Override
    public void onstart() {

    }

    @Override
    public void onfinish() {

    }


    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
