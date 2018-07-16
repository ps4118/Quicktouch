package com.pft.quicktouch.mvp.model;

import com.pft.quicktouch.http.CallBackUtil;
import com.pft.quicktouch.http.OkhttpUtil;
import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.FoodContract;

import okhttp3.Call;

/**
 * 菜品MVP-M
 */
public class FoodModelImpl implements FoodContract.FoodModel {
    /**
     * @param callBack
     */
    @Override
    public void addFood(final ResultCallBack callBack) {
        OkhttpUtil.okHttpGet("", new CallBackUtil.CallBackString() {
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


    @Override
    public void updateFood(final ResultCallBack callBack) {
        OkhttpUtil.okHttpGet("", new CallBackUtil.CallBackString() {
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
