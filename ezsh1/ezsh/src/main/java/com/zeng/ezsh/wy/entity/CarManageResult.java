package com.zeng.ezsh.wy.entity;

/**
 * Title:CarManageResult
 * Description:车辆管理返回给app端的数据
 * @author HAO
 * @date:2017年8月14日 下午8:45:52
 */
public class CarManageResult<T> {
	//请求返回状态
	private int status;
	//返回数据
	private T data;
	//返回信息
	private String message;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CarManageResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CarManageResult(int status, T data, String message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}
	public CarManageResult(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	

}
