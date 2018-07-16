package com.pft.quicktouch.bean;

import java.io.Serializable;

/**
 * 菜品
 */
public class Food implements Serializable {

    private int id;//菜品id
    private String name;//菜名
    private String imgUrl;//图片封面url
    private String price;//原价
    private String oldprice;//原价
    private String saleprice;//折扣价
    private int todayCount;//当天销量

    private String type;//类别（堂食、外卖）
    private int sum;//总销量
    private int status;//状态
    private String orderTime;//菜品下单时间

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOldprice() {
        return oldprice;
    }

    public void setOldprice(String oldprice) {
        this.oldprice = oldprice;
    }

    public String getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(String saleprice) {
        this.saleprice = saleprice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(int todayCount) {
        this.todayCount = todayCount;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", price='" + price + '\'' +
                ", oldprice='" + oldprice + '\'' +
                ", saleprice='" + saleprice + '\'' +
                ", todayCount=" + todayCount +
                ", sum=" + sum +
                ", status=" + status +
                '}';
    }
}
