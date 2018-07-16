package com.pft.quicktouch.mvp.contract;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.view.BaseView;

/**
 * 促销活动
 */
public class SaleContract {
    public interface SaleView extends BaseView {
        //全场折扣
        void getSaleList(String msg);

        void getSaleError(String msg);


    }

    public interface SaleModel {
        void getSale(String url, ResultCallBack callBack);

    }
}
