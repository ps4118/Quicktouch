package com.pft.quicktouch.mvp.contract;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.view.BaseView;

/**
 * 菜品的管理
 * 增加，删除，更改状态
 */
public class FoodContract {
    /**
     * 添加菜品
     */
    public interface FoodView extends BaseView {
        void addSuccess(String msg);

        void addError(String msg);

        void delSuccess(String msg);

        void delError(String msg);

        void updateSuccess(String msg);

        void updateError(String msg);
    }

    /**
     * 删除菜品
     */
    public interface DeleteFoodView extends BaseView {


    }

    /**
     * 更改菜品
     */
    public interface UpdateFoodView extends BaseView {

    }

    /**
     * 菜品model层
     */
    public interface FoodModel {
        //添加菜品
        void addFood(ResultCallBack callBack);

        //删除菜品
        void delFood(int id, ResultCallBack callBack);

        //编辑菜品
        void updateFood(ResultCallBack callBack);
    }


}
