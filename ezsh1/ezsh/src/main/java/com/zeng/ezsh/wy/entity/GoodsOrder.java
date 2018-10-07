package com.zeng.ezsh.wy.entity;

import java.math.BigDecimal;
import java.util.List;

public class GoodsOrder {
    private Integer orderId;

    private Integer pUserId;
    
    private Integer ptManagerId;

    private Integer ptBuildId;
    
    private Integer pMerchantId;

	private String orderDistribution;

	private String linkMan;

	private String linkPhone;

    private String addressContent;

    private String orderSerialNum;

    private String transactionNum;

    private String payTime;

    private String addTime;

    private Integer payStatus;

    private Integer orderStatus;
    
    private BigDecimal usedIntegral;
    
    private BigDecimal totalPrice;

    private Integer delStatus;
    
    private Integer payClassify;
    
	private List<GoodsOrderDetails> orderDetailsList;
    
    private GoodsMerchant merchantInfo;

	public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getpUserId() {
        return pUserId;
    }

    public void setpUserId(Integer pUserId) {
        this.pUserId = pUserId;
    }
    
    public Integer getPtManagerId() {
		return ptManagerId;
	}

	public void setPtManagerId(Integer ptManagerId) {
		this.ptManagerId = ptManagerId;
	}

	public Integer getPtBuildId() {
		return ptBuildId;
	}

	public void setPtBuildId(Integer ptBuildId) {
		this.ptBuildId = ptBuildId;
	}

	public Integer getpMerchantId() {
		return pMerchantId;
	}

	public void setpMerchantId(Integer pMerchantId) {
		this.pMerchantId = pMerchantId;
	}

	public String getOrderDistribution() {
		return orderDistribution;
	}

	public void setOrderDistribution(String orderDistribution) {
		this.orderDistribution = orderDistribution;
	}

    public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone == null ? null : linkPhone.trim();
    }

    public String getAddressContent() {
        return addressContent;
    }

    public void setAddressContent(String addressContent) {
        this.addressContent = addressContent == null ? null : addressContent.trim();
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum == null ? null : orderSerialNum.trim();
    }

    public String getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(String transactionNum) {
        this.transactionNum = transactionNum == null ? null : transactionNum.trim();
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime == null ? null : payTime.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public BigDecimal getUsedIntegral() {
		return usedIntegral;
	}

	public void setUsedIntegral(BigDecimal usedIntegral) {
		this.usedIntegral = usedIntegral;
	}

	public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	
	public Integer getPayClassify() {
		return payClassify;
	}

	public void setPayClassify(Integer payClassify) {
		this.payClassify = payClassify;
	}

	public List<GoodsOrderDetails> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(List<GoodsOrderDetails> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}
	
	
	public GoodsMerchant getMerchantInfo() {
		return merchantInfo;
	}

	public void setMerchantInfo(GoodsMerchant merchantInfo) {
		this.merchantInfo = merchantInfo;
	}
}