package com.pft.quicktouch.mvp.model;

import com.pft.quicktouch.http.CallBackUtil;
import com.pft.quicktouch.http.OkhttpUtil;
import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.FoodManagerUpdateContract;

import okhttp3.Call;

public class FoodManagerUpdateMoelImpl implements FoodManagerUpdateContract.FoodManagerUpdateModel {
    /**
     * 获取分类
     *
     * @param url
     * @param callBack
     */
    @Override
    public void getType(String url, final ResultCallBack callBack) {
        OkhttpUtil.okHttpGet(url, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                callBack.onError(e.toString());
            }

            @Override
            public void onResponse(String response) {

                callBack.onSuccess(response);
            }
        });
    }

    /**
     * 获取对应分类的菜品
     *
     * @param url
     * @param callBack
     */
    @Override
    public void getFoods(String url, final ResultCallBack callBack) {
        OkhttpUtil.okHttpGet(url, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                callBack.onError(e.toString());
            }

            @Override
            public void onResponse(String response) {

                callBack.onSuccess(response);
            }
        });


    }


    /**
     * 删除菜品
     */
    @Override
    public void delFood(String url, final ResultCallBack callBack) {
        OkhttpUtil.okHttpGet(url, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                callBack.onError(e.toString());
            }

            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response);
            }
        });

    }

    /**
     * 加入特价
     *
     * @param url
     * @param callBack
     */
    @Override
    public void addSale(String url, final ResultCallBack callBack) {
        OkhttpUtil.okHttpGet(url, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                callBack.onError(e.toString());
            }

            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response);
            }
        });
    }

    /**
     * 获取当前账户已有特价菜品数量
     *
     * @param url
     * @param callBack
     */
    @Override
    public void getSaleCount(String url, final ResultCallBack callBack) {
        OkhttpUtil.okHttpGet(url, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                callBack.onError(e.toString());
            }

            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response);
            }
        });


    }
}
