package com.pft.quicktouch.mvp.presenter;

import com.pft.quicktouch.bean.Food;
import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.FoodManagerContract;
import com.pft.quicktouch.mvp.model.FoodManagerModelImpl;

import java.util.List;

public class FoodManagerPresenter extends BasePresenter<FoodManagerContract.FoodManagerView> {
    private FoodManagerModelImpl mFoodManagerModel;

    public FoodManagerPresenter() {
        mFoodManagerModel = new FoodManagerModelImpl();
    }

    public void getList() {
        mFoodManagerModel.getFoods(new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().getList(msg);
            }

            @Override
            public void onError(String msg) {
                getView().noMoreData();
            }
        });
    }


    public void updateStatus() {
        mFoodManagerModel.updateStutas(new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().updateSuccess(msg);
            }

            @Override
            public void onError(String msg) {
                getView().updateError(msg);


            }
        });
    }


}
