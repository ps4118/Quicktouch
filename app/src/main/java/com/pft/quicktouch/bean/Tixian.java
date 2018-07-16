package com.pft.quicktouch.bean;

/**
 * 提现流水
 */
public class Tixian {
    //提现时间
    private String time;
    //提现金额
    private String txMoney;
    //手续费
    private String sxMoney;
    //实际到账
    private String dzMoney;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTxMoney() {
        return txMoney;
    }

    public void setTxMoney(String txMoney) {
        this.txMoney = txMoney;
    }

    public String getSxMoney() {
        return sxMoney;
    }

    public void setSxMoney(String sxMoney) {
        this.sxMoney = sxMoney;
    }

    public String getDzMoney() {
        return dzMoney;
    }

    public void setDzMoney(String dzMoney) {
        this.dzMoney = dzMoney;
    }

    @Override
    public String toString() {
        return "Tixian{" +
                "time='" + time + '\'' +
                ", txMoney='" + txMoney + '\'' +
                ", sxMoney='" + sxMoney + '\'' +
                ", dzMoney='" + dzMoney + '\'' +
                '}';
    }
}
