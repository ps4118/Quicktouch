package com.pft.quicktouch.ui.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.pft.quicktouch.mvp.presenter.BasePresenter;
import com.pft.quicktouch.tool.ScreenTool;

import butterknife.ButterKnife;

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {
    public String TAG = getClass().getSimpleName();
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //控件注解
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }


        ButterKnife.bind(this);
//        ScreenTool.initSystemBar(this);
        mPresenter = createPresenter();
        initdata();
        initview();


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
    }


    /**
     * 创建Presenter 对象
     *
     * @return
     */
    protected abstract T createPresenter();

    /**
     * 初始化视图控件
     */
    protected abstract void initview();

    /**
     * 初始化数据
     */

    protected abstract void initdata();

    /**
     * 获取视图id
     *
     * @return
     */
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();


        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }


}
