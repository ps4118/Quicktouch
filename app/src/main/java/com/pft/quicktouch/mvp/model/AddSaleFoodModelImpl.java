package com.pft.quicktouch.mvp.model;

import com.pft.quicktouch.http.CallBackUtil;
import com.pft.quicktouch.http.OkhttpUtil;
import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.AddSaleFoodContract;

import okhttp3.Call;

public class AddSaleFoodModelImpl implements AddSaleFoodContract.AddSaleFoodModel {
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
}
