package com.zeng.ezsh.wy.entity;
/**
 * Title:PlateCost
 * Description:停车相关费用
 * @author HAO
 * @date:2017年8月3日 下午9:26:55
 */
public class PlateCost {
	
	private int userCommunityId;
	
	private String userCommunityName;
	
	private double managementCost;
	
	private double onehourCost;
	
	private double twohourCost;

	public int getUserCommunityId() {
		return userCommunityId;
	}

	public void setUserCommunityId(int userCommunityId) {
		this.userCommunityId = userCommunityId;
	}

	public String getUserCommunityName() {
		return userCommunityName;
	}

	public void setUserCommunityName(String userCommunityName) {
		this.userCommunityName = userCommunityName;
	}

	public double getManagementCost() {
		return managementCost;
	}

	public void setManagementCost(double managementCost) {
		this.managementCost = managementCost;
	}

	public double getOnehourCost() {
		return onehourCost;
	}

	public void setOnehourCost(double onehourCost) {
		this.onehourCost = onehourCost;
	}

	public double getTwohourCost() {
		return twohourCost;
	}

	public void setTwohourCost(double twohourCost) {
		this.twohourCost = twohourCost;
	}

	public PlateCost() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlateCost(int userCommunityId, String userCommunityName,
			double managementCost, double onehourCost, double twohourCost) {
		super();
		this.userCommunityId = userCommunityId;
		this.userCommunityName = userCommunityName;
		this.managementCost = managementCost;
		this.onehourCost = onehourCost;
		this.twohourCost = twohourCost;
	}

	@Override
	public String toString() {
		return "PlateCost [userCommunityId=" + userCommunityId
				+ ", userCommunityName=" + userCommunityName
				+ ", managementCost=" + managementCost + ", onehourCost="
				+ onehourCost + ", twohourCost=" + twohourCost + "]";
	}
	
	
	
	

}
