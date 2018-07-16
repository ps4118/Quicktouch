package com.pft.quicktouch.mvp.contract;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.view.BaseView;

public class SaleDetailsContract {

    public interface SaleDetailsView extends BaseView {
        //全场折扣
        void getAllSaleSuccess(String msg);

        void getAllSaleError(String msg);

        //菜品折扣
        void getFoodSaleSuccess(String msg);

        void getFoodSaleError(String msg);
    }

    public interface SaleDetailsModel {
        void getSale(String url, ResultCallBack callBack);
    }
}
