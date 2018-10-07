package com.zeng.ezsh.wy.entity;

import java.math.BigDecimal;

public class Goods {
    private Integer goodsId;

	private Integer pGoodsInfoId;

    private String sectionOneValue;

    private String sectionOneImage;

    private String sectionTwoValue;

    private String sectionThreeValue;

    private String sectionFourValue;

    private String sectionFiveValue;

    private BigDecimal goodsPrice;

	private Integer goodsAmount;

	private GoodsInfo goodsInfo;

    public GoodsInfo getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	
    public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getpGoodsInfoId() {
        return pGoodsInfoId;
    }

    public void setpGoodsInfoId(Integer pGoodsInfoId) {
        this.pGoodsInfoId = pGoodsInfoId;
    }

    public String getSectionOneValue() {
        return sectionOneValue;
    }

    public void setSectionOneValue(String sectionOneValue) {
        this.sectionOneValue = sectionOneValue == null ? null : sectionOneValue.trim();
    }

    public String getSectionOneImage() {
        return sectionOneImage;
    }

    public void setSectionOneImage(String sectionOneImage) {
        this.sectionOneImage = sectionOneImage == null ? null : sectionOneImage.trim();
    }

    public String getSectionTwoValue() {
        return sectionTwoValue;
    }

    public void setSectionTwoValue(String sectionTwoValue) {
        this.sectionTwoValue = sectionTwoValue == null ? null : sectionTwoValue.trim();
    }

    public String getSectionThreeValue() {
        return sectionThreeValue;
    }

    public void setSectionThreeValue(String sectionThreeValue) {
        this.sectionThreeValue = sectionThreeValue == null ? null : sectionThreeValue.trim();
    }

    public String getSectionFourValue() {
        return sectionFourValue;
    }

    public void setSectionFourValue(String sectionFourValue) {
        this.sectionFourValue = sectionFourValue == null ? null : sectionFourValue.trim();
    }

    public String getSectionFiveValue() {
        return sectionFiveValue;
    }

    public void setSectionFiveValue(String sectionFiveValue) {
        this.sectionFiveValue = sectionFiveValue == null ? null : sectionFiveValue.trim();
    }

    public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	public Integer getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
}