package com.pft.quicktouch.mvp.contract;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.view.BaseView;

/**
 * 菜品更新
 */
public class FoodManagerUpdateContract {
    public interface FoodManagerUpdateView extends BaseView {
        void getType(String msg);

        void delSuccess(String msg);

        void delError(String msg);

        void getFoods(String msg);

        void getSaleCount(String msg);

        /**
         * 获取错误都回调此方法
         */
        void getError(String msg);
    }


    public interface FoodManagerUpdateModel {
        //获取类别

        void getType(String url, ResultCallBack callBack);

        //获取菜品
        void getFoods(String url, ResultCallBack callBack);

        //删除菜品
        void delFood(String url, ResultCallBack callBack);

        //加入特价
        void addSale(String url, ResultCallBack callBack);

        //获取特价菜品数量
        void getSaleCount(String url, ResultCallBack callBack);
    }


}
