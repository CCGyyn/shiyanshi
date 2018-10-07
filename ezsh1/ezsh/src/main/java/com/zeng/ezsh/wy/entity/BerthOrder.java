package com.zeng.ezsh.wy.entity;

import java.util.Date;

public class BerthOrder {
    private Integer id;

    private String berthNumber;

    private Integer userCommunityId;

    private Double money;

    private String outTradeNo;

    private String transactionNum;

    private String payway;

    private Date payTime;

    private Integer payStatu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBerthNumber() {
        return berthNumber;
    }

    public void setBerthNumber(String berthNumber) {
        this.berthNumber = berthNumber == null ? null : berthNumber.trim();
    }

    public Integer getUserCommunityId() {
        return userCommunityId;
    }

    public void setUserCommunityId(Integer userCommunityId) {
        this.userCommunityId = userCommunityId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(String transactionNum) {
        this.transactionNum = transactionNum == null ? null : transactionNum.trim();
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway == null ? null : payway.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getPayStatu() {
        return payStatu;
    }

    public void setPayStatu(Integer payStatu) {
        this.payStatu = payStatu;
    }
}