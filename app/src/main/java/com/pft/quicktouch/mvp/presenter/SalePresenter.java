package com.pft.quicktouch.mvp.presenter;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.SaleContract;
import com.pft.quicktouch.mvp.model.SaleModelImpl;

public class SalePresenter extends BasePresenter<SaleContract.SaleView> {

    private SaleModelImpl mSaleModel;

    public SalePresenter() {
        mSaleModel = new SaleModelImpl();
    }

    /**
     * 全场折扣
     *
     * @param url
     */
    public void getAllSale(String url) {
        mSaleModel.getSale(url, new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().getSaleList(msg);
            }

            @Override
            public void onError(String msg) {
                getView().getSaleError(msg);
            }
        });
    }

}
