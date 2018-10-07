package com.zeng.ezsh.wy.entity;

public class Lock {

	private Integer id;
	
	private String car;

	private String validity;
	
	
	private long delayMillis;// 延迟执行时间 单位毫秒
	private boolean isApproaching;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public long getDelayMillis() {
		return delayMillis;
	}

	public void setDelayMillis(long delayMillis) {
		this.delayMillis = delayMillis;
	}

	public boolean isApproaching() {
		return isApproaching;
	}

	public void setApproaching(boolean isApproaching) {
		this.isApproaching = isApproaching;
	}

	@Override
	public String toString() {
		return "Lock [car=" + car + ", validity=" + validity + "]";
	}
	
	
	
}
