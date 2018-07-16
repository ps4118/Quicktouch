package com.pft.quicktouch.mvp.model;

import android.text.TextUtils;
import android.widget.TextView;

import com.pft.quicktouch.http.CallBackUtil;
import com.pft.quicktouch.http.OkhttpUtil;
import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.AllDiscoutContract;

import okhttp3.Call;

public class AllDiscountModelImpl implements AllDiscoutContract.AllDiscountModel {
    @Override
    public void setDiscount(String dis, String starttime, String endtime, final ResultCallBack callBack) {
        if (TextUtils.isEmpty(starttime) || TextUtils.isEmpty(endtime) || TextUtils.isEmpty(dis)) {
            callBack.onError("请完整设置内容。");

        } else {
            OkhttpUtil.okHttpGet("http://wwww.baidu.com", new CallBackUtil.CallBackString() {

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
}
