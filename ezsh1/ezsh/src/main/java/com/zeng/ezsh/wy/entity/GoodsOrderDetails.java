package com.zeng.ezsh.wy.entity;

import java.math.BigDecimal;

public class GoodsOrderDetails {
    private Integer orderDetailsId;

    private Integer pOrderId;

    private Integer pGoodsId;
    
    private String goodsName;
    
    private String sectionNamesValues;

	private Integer buyAmount;

	private BigDecimal price;

	private String refundContent;

    private String refundImgs;

    private BigDecimal refundMoney;

	private Integer afterSaleStatus;

    private Integer appraiseStatus;
    
    private String synthesizeStatus;//综合状态
    
    private GoodsOrder goodsOrder;
    
	private Goods goods;

	private Goods goodsList;

	public Integer getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(Integer orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public Integer getpOrderId() {
        return pOrderId;
    }

    public void setpOrderId(Integer pOrderId) {
        this.pOrderId = pOrderId;
    }

    public Integer getpGoodsId() {
        return pGoodsId;
    }

    public void setpGoodsId(Integer pGoodsId) {
        this.pGoodsId = pGoodsId;
    }
    
    public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
    
	public String getSectionNamesValues() {
		return sectionNamesValues;
	}

	public void setSectionNamesValues(String sectionNamesValues) {
		this.sectionNamesValues = sectionNamesValues;
	}
	
    public Integer getBuyAmount() {
		return buyAmount;
	}

	public void setBuyAmount(Integer buyAmount) {
		this.buyAmount = buyAmount;
	}
	
    public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

    public String getRefundContent() {
        return refundContent;
    }

    public void setRefundContent(String refundContent) {
        this.refundContent = refundContent == null ? null : refundContent.trim();
    }

    public String getRefundImgs() {
        return refundImgs;
    }

    public void setRefundImgs(String refundImgs) {
        this.refundImgs = refundImgs == null ? null : refundImgs.trim();
    }

    public BigDecimal getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}

    public Integer getAfterSaleStatus() {
        return afterSaleStatus;
    }

    public void setAfterSaleStatus(Integer afterSaleStatus) {
        this.afterSaleStatus = afterSaleStatus;
    }

	public Integer getAppraiseStatus() {
		return appraiseStatus;
	}

	public void setAppraiseStatus(Integer appraiseStatus) {
		this.appraiseStatus = appraiseStatus;
	}

	public String getSynthesizeStatus() {
		return synthesizeStatus;
	}

	public void setSynthesizeStatus(String synthesizeStatus) {
		this.synthesizeStatus = synthesizeStatus;
	}
	
    public GoodsOrder getGoodsOrder() {
		return goodsOrder;
	}

	public void setGoodsOrder(GoodsOrder goodsOrder) {
		this.goodsOrder = goodsOrder;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	public Goods getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(Goods goodsList) {
		this.goodsList = goodsList;
	}

}