package com.zeng.ezsh.wy.entity;

public class RepairRecordVO {
	
	private String communityName;
	//报单总数
	private int total;
	//未派工数
	private int waitForWorker;
	//已派工数
	private int sendWorker;
	//完工数
	private int complete;
	//已审核数
	private int audited;
	
	
	public RepairRecordVO(String communityName, int total, int waitForWorker,
			int sendWorker, int complete, int audited) {
		super();
		this.communityName = communityName;
		this.total = total;
		this.waitForWorker = waitForWorker;
		this.sendWorker = sendWorker;
		this.complete = complete;
		this.audited = audited;
	}
	
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getWaitForWorker() {
		return waitForWorker;
	}
	public void setWaitForWorker(int waitForWorker) {
		this.waitForWorker = waitForWorker;
	}
	public int getSendWorker() {
		return sendWorker;
	}
	public void setSendWorker(int sendWorker) {
		this.sendWorker = sendWorker;
	}
	public int getComplete() {
		return complete;
	}
	public void setComplete(int complete) {
		this.complete = complete;
	}
	public int getAudited() {
		return audited;
	}
	public void setAudited(int audited) {
		this.audited = audited;
	}
	public RepairRecordVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RepairRecordVO [communityName=" + communityName + ", total="
				+ total + ", waitForWorker=" + waitForWorker + ", sendWorker="
				+ sendWorker + ", complete=" + complete + ", audited="
				+ audited + "]";
	}
	
	
	
}
