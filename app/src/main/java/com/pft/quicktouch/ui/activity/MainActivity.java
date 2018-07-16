package com.pft.quicktouch.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.pft.quicktouch.R;
import com.pft.quicktouch.tool.ToastTool;
import com.pft.quicktouch.ui.fragment.CountFragment;
import com.pft.quicktouch.ui.fragment.FoodFragment;
import com.pft.quicktouch.ui.fragment.MimeFragment;
import com.pft.quicktouch.ui.fragment.OrderFragment;
import com.pft.quicktouch.ui.fragment.SaleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements
        OnCheckedChangeListener {
    OrderFragment order = null;
    CountFragment count = null;
    FoodFragment food = null;
    SaleFragment sale = null;
    MimeFragment mime = null;
    /**
     * 主页所有Fragment集合
     */
    List<Fragment> lf = null;
    @BindView(R.id.base_rg)
    RadioGroup baseRg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initview();
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

    }

    /**
     * 初始化控件
     */

    private void initview() {

        lf = new ArrayList<Fragment>();
        order = new OrderFragment();
        sale = new SaleFragment();
        food = new FoodFragment();
        count = new CountFragment();
        mime = new MimeFragment();
        baseRg.setOnCheckedChangeListener(this);
        // 默认显示第一个fragment
        lf.add(order);
        lf.add(food);
        lf.add(count);
        lf.add(sale);
        lf.add(mime);
        addFragmentToStack(order);

    }

    /**
     * 单选按钮组选择监听
     */

    @Override
    public void onCheckedChanged(RadioGroup group, int p) {
        // TODO Auto-generated method stub
        switch (p) {
            case R.id.order:
                addFragmentToStack(order);
                break;
            case R.id.food:
                addFragmentToStack(food);
                break;
            case R.id.sale:
                addFragmentToStack(sale);
                break;
            case R.id.count:
                addFragmentToStack(count);
                break;
            case R.id.mime:
                addFragmentToStack(mime);
                break;
        }
    }


    /**
     * 隐藏所有fragment
     *
     * @param ft
     */
    public void hidef(FragmentTransaction ft) {
        for (int i = 0; i < lf.size(); i++) {
            ft.hide(lf.get(i));
        }
    }

    FragmentTransaction ft = null;
    boolean isRequesting = true;

    /**
     * 先隐藏所有Fragment，再显示指定Fragment
     *
     * @param fragment
     */
    public void addFragmentToStack(Fragment fragment) {
        ft = getSupportFragmentManager().beginTransaction();
        if (lf.size() > 0) {
            hidef(ft);
        }
        ft.addToBackStack(null);
        if (!fragment.isAdded()) {
            ft.add(R.id.base_fl, fragment).show(fragment)
                    .commitAllowingStateLoss();
        } else {
            ft.show(fragment).commitAllowingStateLoss();
        }
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();

    }

    //退出时的时间
    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastTool.showToast(MainActivity.this, "再点击一次退出");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }


}
