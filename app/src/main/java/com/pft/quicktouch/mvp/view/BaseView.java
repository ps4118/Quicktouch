package com.pft.quicktouch.mvp.view;

/**
 * View基类
 */
public interface BaseView {
    //网络请求开始（loadding view）
    void onstart();

    //网络请求结束
    void onfinish();
}
