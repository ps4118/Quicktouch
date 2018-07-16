package com.pft.quicktouch.mvp.contract;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.view.BaseView;

/**
 * 全场折扣
 */
public class AllDiscoutContract {
    public interface AllDiscountView extends BaseView {
        void setSuccess(String msg);

        void setError(String msg);
    }

    public interface AllDiscountModel {
        void setDiscount(String dis, String starttime, String endtime, ResultCallBack callBack);
    }
}
