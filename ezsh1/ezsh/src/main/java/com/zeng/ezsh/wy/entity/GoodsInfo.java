package com.zeng.ezsh.wy.entity;
import java.math.BigDecimal;
import java.util.List;

public class GoodsInfo {
	private Integer goodsInfoId;

    private Integer pGoodsClassfyId;

    private Integer pManagerId;

    private Integer pMerchantId;
    
    private String goodsName;

    private String goodsSlideShow;

    private String goodsCarriage;

    private String goodsImageText;

    private String goodsSourceArea;
    
    private String goodsDistribution;

	private String goodsTimeMark;

    private String goodsBrand;
    
    private String goodsParameter;

	private String goodsTotalSaleAmount;

	private BigDecimal goodsMinPrice;

    private BigDecimal goodsMaxPrice;
    
	private String sectionOneName;

    private String sectionTwoName;

    private String sectionThreeName;

    private String sectionFourName;

    private String sectionFiveName;

    private String addtime;
    
    private String goodsShowClassfy;

	private List<Goods> goodsList;
    
    private List<GoodsDistribution> goodsDistributionsList;
    
    private GoodsClassfy goodsClassfyInfo;
    
    private GoodsDiscounts goodsDiscountsInfo;
    
    private GoodsMerchant goodsMerchantInfo;
    
    public GoodsDiscounts getGoodsDiscountsInfo() {
		return goodsDiscountsInfo;
	}

	public void setGoodsDiscountsInfo(GoodsDiscounts goodsDiscountsInfo) {
		this.goodsDiscountsInfo = goodsDiscountsInfo;
	}

	public GoodsClassfy getGoodsClassfyInfo() {
		return goodsClassfyInfo;
	}

	public void setGoodsClassfyInfo(GoodsClassfy goodsClassfyInfo) {
		this.goodsClassfyInfo = goodsClassfyInfo;
	}

	public List<GoodsDistribution> getGoodsDistributionsList() {
		return goodsDistributionsList;
	}

	public void setGoodsDistributionsList(
			List<GoodsDistribution> goodsDistributionsList) {
		this.goodsDistributionsList = goodsDistributionsList;
	}

	public Integer getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Integer goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Integer getpGoodsClassfyId() {
        return pGoodsClassfyId;
    }

    public void setpGoodsClassfyId(Integer pGoodsClassfyId) {
        this.pGoodsClassfyId = pGoodsClassfyId;
    }

    public Integer getpManagerId() {
        return pManagerId;
    }

    public void setpManagerId(Integer pManagerId) {
        this.pManagerId = pManagerId;
    }
    
    
    public Integer getpMerchantId() {
		return pMerchantId;
	}

	public void setpMerchantId(Integer pMerchantId) {
		this.pMerchantId = pMerchantId;
	}

	public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsSlideShow() {
        return goodsSlideShow;
    }

    public void setGoodsSlideShow(String goodsSlideShow) {
        this.goodsSlideShow = goodsSlideShow == null ? null : goodsSlideShow.trim();
    }

    public String getGoodsCarriage() {
        return goodsCarriage;
    }

    public void setGoodsCarriage(String goodsCarriage) {
        this.goodsCarriage = goodsCarriage == null ? null : goodsCarriage.trim();
    }

    public String getGoodsImageText() {
        return goodsImageText;
    }

    public void setGoodsImageText(String goodsImageText) {
        this.goodsImageText = goodsImageText == null ? null : goodsImageText.trim();
    }

    public String getGoodsSourceArea() {
        return goodsSourceArea;
    }

    public void setGoodsSourceArea(String goodsSourceArea) {
        this.goodsSourceArea = goodsSourceArea == null ? null : goodsSourceArea.trim();
    }
      
    public String getGoodsDistribution() {
		return goodsDistribution;
	}

	public void setGoodsDistribution(String goodsDistribution) {
		this.goodsDistribution = goodsDistribution;
	}
	
    public String getGoodsTimeMark() {
        return goodsTimeMark;
    }

    public void setGoodsTimeMark(String goodsTimeMark) {
        this.goodsTimeMark = goodsTimeMark == null ? null : goodsTimeMark.trim();
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand == null ? null : goodsBrand.trim();
    }
    
    public String getGoodsParameter() {
		return goodsParameter;
	}

	public void setGoodsParameter(String goodsParameter) {
		this.goodsParameter = goodsParameter;
	}
	
    public String getGoodsTotalSaleAmount() {
		return goodsTotalSaleAmount;
	}

	public void setGoodsTotalSaleAmount(String goodsTotalSaleAmount) {
		this.goodsTotalSaleAmount = goodsTotalSaleAmount;
	}

	public BigDecimal getGoodsMinPrice() {
		return goodsMinPrice;
	}

	public void setGoodsMinPrice(BigDecimal goodsMinPrice) {
		this.goodsMinPrice = goodsMinPrice;
	}

	public BigDecimal getGoodsMaxPrice() {
		return goodsMaxPrice;
	}

	public void setGoodsMaxPrice(BigDecimal goodsMaxPrice) {
		this.goodsMaxPrice = goodsMaxPrice;
	}
	
    public String getSectionOneName() {
        return sectionOneName;
    }
    
    public void setSectionOneName(String sectionOneName) {
        this.sectionOneName = sectionOneName == null ? null : sectionOneName.trim();
    }

    public String getSectionTwoName() {
        return sectionTwoName;
    }

    public void setSectionTwoName(String sectionTwoName) {
        this.sectionTwoName = sectionTwoName == null ? null : sectionTwoName.trim();
    }

    public String getSectionThreeName() {
        return sectionThreeName;
    }

    public void setSectionThreeName(String sectionThreeName) {
        this.sectionThreeName = sectionThreeName == null ? null : sectionThreeName.trim();
    }

    public String getSectionFourName() {
        return sectionFourName;
    }

    public void setSectionFourName(String sectionFourName) {
        this.sectionFourName = sectionFourName == null ? null : sectionFourName.trim();
    }

    public String getSectionFiveName() {
        return sectionFiveName;
    }

    public void setSectionFiveName(String sectionFiveName) {
        this.sectionFiveName = sectionFiveName == null ? null : sectionFiveName.trim();
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }
    
    public String getGoodsShowClassfy() {
		return goodsShowClassfy;
	}

	public void setGoodsShowClassfy(String goodsShowClassfy) {
		this.goodsShowClassfy = goodsShowClassfy;
	}
	
    public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public GoodsMerchant getGoodsMerchantInfo() {
		return goodsMerchantInfo;
	}

	public void setGoodsMerchantInfo(GoodsMerchant goodsMerchantInfo) {
		this.goodsMerchantInfo = goodsMerchantInfo;
	}
	
}