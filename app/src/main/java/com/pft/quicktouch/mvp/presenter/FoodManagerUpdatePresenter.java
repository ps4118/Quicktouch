package com.pft.quicktouch.mvp.presenter;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.FoodManagerUpdateContract;
import com.pft.quicktouch.mvp.model.FoodManagerUpdateMoelImpl;

public class FoodManagerUpdatePresenter extends BasePresenter<FoodManagerUpdateContract.FoodManagerUpdateView> {

    private FoodManagerUpdateMoelImpl mFoodManagerUpdateMoel;

    public FoodManagerUpdatePresenter() {
        mFoodManagerUpdateMoel = new FoodManagerUpdateMoelImpl();
    }

    /**
     * 获取对应菜品
     */

    public void getFoods(String url) {
        mFoodManagerUpdateMoel.getFoods(url, new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().getType(msg);
            }

            @Override
            public void onError(String msg) {
                getView().getError(msg);
            }
        });
    }

    /**
     * 获取分类
     */

    public void getTypes(String url) {
        mFoodManagerUpdateMoel.getType(url, new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().getType(msg);
            }

            @Override
            public void onError(String msg) {
                getView().getError(msg);
            }
        });
    }


    /**
     * 删除菜品
     */
    public void delFood(String url) {
        mFoodManagerUpdateMoel.delFood(url, new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().delSuccess(msg);
            }

            @Override
            public void onError(String msg) {
                getView().delError(msg);
            }
        });
    }

    /**
     * 加入特价
     */
    public void addSale(String url) {
        mFoodManagerUpdateMoel.addSale(url, new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().delSuccess(msg);
            }

            @Override
            public void onError(String msg) {
                getView().delError(msg);
            }
        });
    }

    /**
     * 获取当前账户已设置的也加菜品数量
     */
    public void getSaleCount(String url) {
        mFoodManagerUpdateMoel.getSaleCount(url, new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().delSuccess(msg);
            }

            @Override
            public void onError(String msg) {
                getView().delError(msg);
            }
        });
    }

}
