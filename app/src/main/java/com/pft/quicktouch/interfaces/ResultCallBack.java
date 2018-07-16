package com.pft.quicktouch.interfaces;

public interface ResultCallBack {
    /**
     * 成功回调
     */
    void onSuccess(String msg);

    /**
     * 失败回调
     */
    void onError(String msg);
}
