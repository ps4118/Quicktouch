package com.pft.quicktouch.interfaces;

/**
 * 底部弹窗的点击监听接口
 */
public interface BottomDialogListener {

    //具体动作,action表明操作
    void handle(String action);

    //取消
    void cancle();
}
