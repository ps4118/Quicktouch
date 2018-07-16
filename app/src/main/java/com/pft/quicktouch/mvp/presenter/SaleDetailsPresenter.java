package com.pft.quicktouch.mvp.presenter;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.SaleDetailsContract;
import com.pft.quicktouch.mvp.model.SaleDetailsModelImpl;

public class SaleDetailsPresenter extends BasePresenter<SaleDetailsContract.SaleDetailsView> {

    private SaleDetailsModelImpl mSaleDetailsModel;

    public SaleDetailsPresenter() {
        mSaleDetailsModel = new SaleDetailsModelImpl();
    }

    /**
     * 获取全场折扣
     */
    public void getAllSale(String url) {
        mSaleDetailsModel.getSale(url, new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().getAllSaleSuccess(msg);
            }

            @Override
            public void onError(String msg) {
                getView().getAllSaleError(msg);
            }
        });

    }

    /**
     * 获取菜品折扣
     */
    public void getFoodSale(String url) {
        mSaleDetailsModel.getSale(url, new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().getFoodSaleSuccess(msg);
            }

            @Override
            public void onError(String msg) {
                getView().getFoodSaleError(msg);
            }
        });

    }

}
