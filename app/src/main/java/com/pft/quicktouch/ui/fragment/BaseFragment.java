package com.pft.quicktouch.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pft.quicktouch.mvp.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    protected T mPresenter;
    private Context mContext;
    //定义一个View用来保存Fragment创建的时候使用打气筒工具进行的布局获取对象的存储
    protected View view;
    private Unbinder unbinder;

    /**
     * 当Fragment进行创建的时候执行的方法
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();//创建presenter

    }

    /**
     * 这个方法是关于Fragment完成创建的过程中，进行界面填充的方法,该方法返回的是一个view对象
     * 在这个对象中封装的就是Fragment对应的布局
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(getLayoutId(), null);
        }
        mContext = getContext();
        unbinder = ButterKnife.bind(this, view);
        initdata();
        initview();


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     *
     */
    /**
     * 创建Presenter对象
     */
    protected abstract T createPresenter();

    @Override
    public void onResume() {
        super.onResume();
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    /**
     * 初始化控件视图
     */

    public abstract void initview();

    /**
     * 初始化数据
     */
    public abstract void initdata();

    /**
     * 获取layoutIdd
     *
     * @return
     */
    public abstract int getLayoutId();
}
