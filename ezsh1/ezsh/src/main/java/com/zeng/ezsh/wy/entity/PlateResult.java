package com.zeng.ezsh.wy.entity;

public class PlateResult<T> {
	
	private boolean success;
	private T data;
	private String error;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public PlateResult(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}
	public PlateResult(boolean success, String result) {
		super();
		if(success == false){
		this.success = success;
		this.error = result;
		}else{
			this.success = success;
			this.data = (T) result;
		}
	}
	
	
	

}
