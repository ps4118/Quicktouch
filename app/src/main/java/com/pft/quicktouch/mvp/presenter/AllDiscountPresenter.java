package com.pft.quicktouch.mvp.presenter;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.AllDiscoutContract;
import com.pft.quicktouch.mvp.model.AllDiscountModelImpl;

public class AllDiscountPresenter extends BasePresenter<AllDiscoutContract.AllDiscountView> {

    private AllDiscountModelImpl mAllDiscountModel;

    public AllDiscountPresenter() {
        mAllDiscountModel = new AllDiscountModelImpl();
    }


    public void setDiscount(String dis, String starttime, String endtime) {
        mAllDiscountModel.setDiscount(dis, starttime, endtime, new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().setSuccess(msg);
            }

            @Override
            public void onError(String msg) {
                getView().setError(msg);
            }
        });
    }
}
