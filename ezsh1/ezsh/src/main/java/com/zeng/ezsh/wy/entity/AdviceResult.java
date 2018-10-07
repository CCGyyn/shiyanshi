package com.zeng.ezsh.wy.entity;

public class AdviceResult<T>{
		//请求返回状态,1表示成功，0表示失败
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
		public AdviceResult() {
			super();
			// TODO Auto-generated constructor stub
		}
		public AdviceResult(int status, T data, String message) {
			super();
			this.status = status;
			this.data = data;
			this.message = message;
		}
		public AdviceResult(int status, String message) {
			super();
			this.status = status;
			this.message = message;
		}
}
