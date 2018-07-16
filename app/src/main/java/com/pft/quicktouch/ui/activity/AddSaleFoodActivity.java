package com.pft.quicktouch.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.Food;
import com.pft.quicktouch.interfaces.DateChooseListener;
import com.pft.quicktouch.mvp.contract.AddSaleFoodContract;
import com.pft.quicktouch.mvp.presenter.AddSaleFoodPresenter;
import com.pft.quicktouch.tool.NetTool;
import com.pft.quicktouch.tool.ToastTool;
import com.pft.quicktouch.view.CustomDatePicker;
import com.pft.quicktouch.view.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSaleFoodActivity extends BaseActivity<AddSaleFoodContract.AddSaleFoodView, AddSaleFoodPresenter> implements AddSaleFoodContract.AddSaleFoodView, TitleBar.TitleBarClickListener {
    public static final String FOOD_INFO = AddSaleFoodActivity.class.getSimpleName();

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.chooseFood)
    TextView chooseFood;
    @BindView(R.id.foodImg)
    ImageView foodImg;
    @BindView(R.id.foodname)
    TextView foodname;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.todayCount)
    TextView todayCount;
    @BindView(R.id.sum)
    TextView sum;
    @BindView(R.id.youhuo)
    TextView youhuo;
    @BindView(R.id.shouqing)
    TextView shouqing;
    @BindView(R.id.xiajia)
    TextView xiajia;
    @BindView(R.id.handle_ll)
    LinearLayout handleLl;
    @BindView(R.id.count)
    EditText count;
    @BindView(R.id.reduce)
    ImageView reduce;
    @BindView(R.id.disTv)
    TextView disTv;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.starttime)
    TextView starttime;
    @BindView(R.id.endtime)
    TextView endtime;
    View view;

    @Override
    protected AddSaleFoodPresenter createPresenter() {
        return new AddSaleFoodPresenter();
    }

    @Override
    protected void initview() {
        titlebar.setTitle("特价菜品");
        titlebar.setTopBarClickListener(this);

        view = findViewById(R.id.item);

    }

    /**
     * 提交
     */
    public void submit(View view) {
        String zk = count.getText().toString().trim();
        if (food == null || startStr.equals("") || endStr.equals("") || zk.equals("")) {
            ToastTool.showToast(AddSaleFoodActivity.this, "请完成所有内容的设置");
        } else if (!NetTool.checkNetandWifi(AddSaleFoodActivity.this)) {
            ToastTool.showToast(AddSaleFoodActivity.this, "请检查网络设置");
        } else {
            //开始提交

        }
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_sale_food;
    }

    @Override
    public void addSuccess(String msg) {

    }

    @Override
    public void addError(String msg) {

    }

    @OnClick(R.id.chooseFood)
    public void chooseFood() {
        Intent intent = new Intent(AddSaleFoodActivity.this, FoodListActivity.class);
        startActivityForResult(intent, 1);

    }

    @Override
    public void onstart() {

    }

    @Override
    public void onfinish() {

    }

    Food food = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            //获取选中的菜品
            food = (Food) data.getSerializableExtra("food");
            Log.i(TAG, "选中菜品信息-->" + food.toString());
            if (food != null) {
                view.setVisibility(View.VISIBLE);
                chooseFood.setText("更换菜品");
                //设置信息
                foodname.setText(food.getName());
                price.setText(food.getPrice());
                sum.setText("总销量：" + food.getSum());
                todayCount.setText("今日销量" + food.getTodayCount());


            }
        }
    }

    String startStr = "", endStr = "";

    @OnClick(R.id.starttime)
    public void setStart() {
        CustomDatePicker picker = new CustomDatePicker(AddSaleFoodActivity.this, new DateChooseListener() {
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
        CustomDatePicker picker = new CustomDatePicker(AddSaleFoodActivity.this, new DateChooseListener() {
            @Override
            public void dateChoose(String time) {
                endStr = time;
                endtime.setText(endStr);
            }
        });
        picker.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}
