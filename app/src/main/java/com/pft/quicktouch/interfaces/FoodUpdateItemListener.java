package com.pft.quicktouch.interfaces;

/**
 * 菜品编辑item点击监听
 */
public interface FoodUpdateItemListener {
    //删除菜品
    void del(int position);

    //编辑
    void update(int position);

    //加入特价
    void addSale(int position);
}
