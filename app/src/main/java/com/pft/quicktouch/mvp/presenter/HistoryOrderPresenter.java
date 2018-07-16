package com.pft.quicktouch.mvp.presenter;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.HistoryOrderContract;
import com.pft.quicktouch.mvp.model.HistoryOrderModelImpl;

/**
 * 主页面历史订单mvp-p
 */
public class HistoryOrderPresenter extends BasePresenter<HistoryOrderContract.HistoryOrderView> {
    private HistoryOrderModelImpl mHistoryOrderModel;

    public HistoryOrderPresenter() {
        mHistoryOrderModel = new HistoryOrderModelImpl();

    }

    //获取历史订单
    public void getOrders(String url) {
        mHistoryOrderModel.getOrders(url, new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().getSuccess(msg);
            }

            @Override
            public void onError(String msg) {
                getView().getError(msg);

            }
        });
    }

}
