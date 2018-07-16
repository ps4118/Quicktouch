package com.pft.quicktouch.mvp.contract;

import com.pft.quicktouch.bean.Food;
import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.view.BaseView;

import java.util.List;

/**
 * 菜品管理
 */
public class FoodManagerContract {

    public interface FoodManagerView extends BaseView {
        /**
         * 获取列表
         */
        void getList(String msg);

        /**
         * 没有更多数据
         */
        void noMoreData();

        /**
         * 更改菜品状态成功
         */
        void updateSuccess(String msg);

        /**
         * 更改菜品状态失败
         */
        void updateError(String msg);
    }

    public interface FoodManagerModel {
        /**
         * 获取菜品列表
         */
        void getFoods(ResultCallBack callBack);

        /**
         * 更改菜品状态
         */

        void updateStutas(ResultCallBack callBack);


    }
}
