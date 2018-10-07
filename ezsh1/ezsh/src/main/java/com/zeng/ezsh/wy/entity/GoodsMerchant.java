package com.zeng.ezsh.wy.entity;

public class GoodsMerchant {
    private Integer merchantId;

    private Integer pManagerId;

    private String managerName;

    private String merchantName;

    private String merchantLinkPhone;

    private String merchantDescr;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getpManagerId() {
        return pManagerId;
    }

    public void setpManagerId(Integer pManagerId) {
        this.pManagerId = pManagerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getMerchantLinkPhone() {
        return merchantLinkPhone;
    }

    public void setMerchantLinkPhone(String merchantLinkPhone) {
        this.merchantLinkPhone = merchantLinkPhone == null ? null : merchantLinkPhone.trim();
    }

    public String getMerchantDescr() {
        return merchantDescr;
    }

    public void setMerchantDescr(String merchantDescr) {
        this.merchantDescr = merchantDescr == null ? null : merchantDescr.trim();
    }
}