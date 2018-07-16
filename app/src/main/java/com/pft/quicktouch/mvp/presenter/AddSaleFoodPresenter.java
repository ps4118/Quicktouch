package com.pft.quicktouch.mvp.presenter;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.AddSaleFoodContract;
import com.pft.quicktouch.mvp.model.AddSaleFoodModelImpl;

public class AddSaleFoodPresenter extends BasePresenter<AddSaleFoodContract.AddSaleFoodView> {

    private AddSaleFoodModelImpl mAddSaleFoodModel;

    public AddSaleFoodPresenter() {
        mAddSaleFoodModel = new AddSaleFoodModelImpl();
    }

    /**
     * 添加特价菜品
     */
    public void addSaleFood(String url) {
        mAddSaleFoodModel.addSale(url, new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().addSuccess(msg);
            }

            @Override
            public void onError(String msg) {
                getView().addError(msg);
            }
        });
    }
}

