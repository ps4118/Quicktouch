package com.pft.quicktouch.mvp.contract;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.view.BaseView;

/**
 * 提现流水
 */
public class TixianContract {

    public interface TixianView extends BaseView {
        void txSuccess(String msg);

        void txError(String msg);
    }

    public interface TixianModle {
        void getTx(String url, ResultCallBack callBack);
    }
}
