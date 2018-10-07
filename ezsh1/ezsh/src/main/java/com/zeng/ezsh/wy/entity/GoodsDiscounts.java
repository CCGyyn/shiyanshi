package com.zeng.ezsh.wy.entity;

public class GoodsDiscounts {
    private Integer pGoodsInfoId;

    private String showBackgroundImage;

    private String showSectionOneValue;

    private String showSectionTwoValue;

    private String showSectionThreeValue;

    public Integer getpGoodsInfoId() {
        return pGoodsInfoId;
    }

    public void setpGoodsInfoId(Integer pGoodsInfoId) {
        this.pGoodsInfoId = pGoodsInfoId;
    }

    public String getShowBackgroundImage() {
        return showBackgroundImage;
    }

    public void setShowBackgroundImage(String showBackgroundImage) {
        this.showBackgroundImage = showBackgroundImage == null ? null : showBackgroundImage.trim();
    }

    public String getShowSectionOneValue() {
        return showSectionOneValue;
    }

    public void setShowSectionOneValue(String showSectionOneValue) {
        this.showSectionOneValue = showSectionOneValue == null ? null : showSectionOneValue.trim();
    }

    public String getShowSectionTwoValue() {
        return showSectionTwoValue;
    }

    public void setShowSectionTwoValue(String showSectionTwoValue) {
        this.showSectionTwoValue = showSectionTwoValue == null ? null : showSectionTwoValue.trim();
    }

    public String getShowSectionThreeValue() {
        return showSectionThreeValue;
    }

    public void setShowSectionThreeValue(String showSectionThreeValue) {
        this.showSectionThreeValue = showSectionThreeValue == null ? null : showSectionThreeValue.trim();
    }
}