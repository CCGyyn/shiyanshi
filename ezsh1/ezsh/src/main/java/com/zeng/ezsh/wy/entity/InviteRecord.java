package com.zeng.ezsh.wy.entity;
/**
 * Title:InviteRecord
 * Description:用户邀请外来访客记录
 * @author HAO
 * @date:2017年8月3日 下午6:09:30
 */
public class InviteRecord {
	
	private int id;
	
	private String userPhone;
	
	private String invitePlate;
	
	private int userCommunityId;
	
	private int isPay;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getInvitePlate() {
		return invitePlate;
	}

	public void setInvitePlate(String invitePlate) {
		this.invitePlate = invitePlate;
	}

	public int getUserCommunityId() {
		return userCommunityId;
	}

	public void setUserCommunityId(int userCommunityId) {
		this.userCommunityId = userCommunityId;
	}

	public int getIsPay() {
		return isPay;
	}

	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}

	public InviteRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InviteRecord(String userPhone, String invitePlate,
			int userCommunityId) {
		super();
		this.userPhone = userPhone;
		this.invitePlate = invitePlate;
		this.userCommunityId = userCommunityId;
	}

	@Override
	public String toString() {
		return "InviteRecord [id=" + id + ", userPhone=" + userPhone
				+ ", invitePlate=" + invitePlate + ", userCommunityId="
				+ userCommunityId + ", isPay=" + isPay + "]";
	}
	
	

}
