package com.pft.quicktouch.mvp.contract;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.view.BaseView;

/**
 * 添加特价菜品
 */
public class AddSaleFoodContract {
    public interface AddSaleFoodView extends BaseView {
        void addSuccess(String msg);

        void addError(String msg);
    }

    public interface AddSaleFoodModel {
        void addSale(String url, ResultCallBack callBack);
    }
}
