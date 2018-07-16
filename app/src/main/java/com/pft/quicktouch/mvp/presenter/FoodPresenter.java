
package com.pft.quicktouch.mvp.presenter;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.FoodContract;
import com.pft.quicktouch.mvp.model.FoodModelImpl;

public class FoodPresenter extends BasePresenter<FoodContract.FoodView> {

    FoodModelImpl mFoodModel;

    public FoodPresenter() {
        mFoodModel = new FoodModelImpl();
    }

    /**
     * 添加菜品
     */
    public void addFood() {
        mFoodModel.addFood(new ResultCallBack() {
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


    /**
     * 更改菜品
     */
    public void updateFood() {
        mFoodModel.updateFood(new ResultCallBack() {
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
