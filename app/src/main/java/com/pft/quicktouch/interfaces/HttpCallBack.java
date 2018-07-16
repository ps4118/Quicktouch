package com.pft.quicktouch.interfaces;

/**
 * 网络请求回调
 */
public interface HttpCallBack {
    /**
     * 成功回调
     */

    void onSuccess(String msg);

    /**
     * 失败回调
     *
     * @param msg 错误提示
     */
    void onError(String msg);
}
