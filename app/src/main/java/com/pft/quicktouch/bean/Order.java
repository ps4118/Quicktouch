package com.pft.quicktouch.bean;

import java.util.List;

/**
 * 订单
 */
public class Order {
    private int id;//订单Id
    private String daytime;//订单日期
    private String total;//总金
    private List<Food> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Food> getList() {
        return list;
    }

    public void setList(List<Food> list) {
        this.list = list;
    }
}
