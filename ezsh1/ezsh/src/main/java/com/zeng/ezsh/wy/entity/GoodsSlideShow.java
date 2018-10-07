package com.zeng.ezsh.wy.entity;

public class GoodsSlideShow {
    private Integer goodsSlideShowId;

    private String slideShowImgUrl;

    private Integer pManagerId;

    public Integer getGoodsSlideShowId() {
        return goodsSlideShowId;
    }

    public void setGoodsSlideShowId(Integer goodsSlideShowId) {
        this.goodsSlideShowId = goodsSlideShowId;
    }

    public String getSlideShowImgUrl() {
        return slideShowImgUrl;
    }

    public void setSlideShowImgUrl(String slideShowImgUrl) {
        this.slideShowImgUrl = slideShowImgUrl == null ? null : slideShowImgUrl.trim();
    }

    public Integer getpManagerId() {
        return pManagerId;
    }

    public void setpManagerId(Integer pManagerId) {
        this.pManagerId = pManagerId;
    }
}