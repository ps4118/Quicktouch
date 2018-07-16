package com.pft.quicktouch.mvp.contract;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.view.BaseView;

/**
 * 历史订单
 */
public class HistoryOrderContract {

    public interface HistoryOrderView extends BaseView {
        void getSuccess(String msg);

        void getError(String msg);
    }

    public interface HistoryOrderModel {
        void getOrders(String url, ResultCallBack callBack);
    }

}
