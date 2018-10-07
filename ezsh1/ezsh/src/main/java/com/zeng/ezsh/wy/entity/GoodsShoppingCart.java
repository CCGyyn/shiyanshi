package com.zeng.ezsh.wy.entity;

public class GoodsShoppingCart {
    private Integer shoppingCartId;

    private Integer pUserId;

    private Integer pGoodsId;

    private Integer pManagerId;
    
    private Integer ptMerchantId;
    
    private Integer goodsAmount;
    
    private Goods goods;
    
    private GoodsMerchant goodsMerchantInfo;
    
	public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Integer getpUserId() {
        return pUserId;
    }

    public void setpUserId(Integer pUserId) {
        this.pUserId = pUserId;
    }

    public Integer getpGoodsId() {
        return pGoodsId;
    }

    public void setpGoodsId(Integer pGoodsId) {
        this.pGoodsId = pGoodsId;
    }

    public Integer getpManagerId() {
        return pManagerId;
    }

    public void setpManagerId(Integer pManagerId) {
        this.pManagerId = pManagerId;
    }
    
    public Integer getPtMerchantId() {
		return ptMerchantId;
	}

	public void setPtMerchantId(Integer ptMerchantId) {
		this.ptMerchantId = ptMerchantId;
	}

	public Integer getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Integer goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public GoodsMerchant getGoodsMerchantInfo() {
		return goodsMerchantInfo;
	}

	public void setGoodsMerchantInfo(GoodsMerchant goodsMerchantInfo) {
		this.goodsMerchantInfo = goodsMerchantInfo;
	}
	
}