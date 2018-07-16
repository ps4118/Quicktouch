package com.pft.quicktouch.mvp.presenter;

import com.pft.quicktouch.mvp.view.BaseView;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * Presenter基类
 */
public class BasePresenter<T> {
    //View接口类型的软引用
    public Reference<T> mViewRef;

    public void attachView(T view) {
        //建立关系
        mViewRef = new SoftReference<T>(view);
    }

    /**
     * 获取绑定的view
     *
     * @return
     */
    public T getView() {
        return mViewRef.get();
    }

    /**
     * view是否绑定
     *
     * @return
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 解绑view
     */
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
        }
    }

}
