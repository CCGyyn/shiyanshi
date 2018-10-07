package com.zeng.ezsh.wy.entity;

/**
 * Title:HouseKeepingResult
 * Description:家政服务模块返回给app端的数据
 * @author HAO
 * @date:2017年8月19日 下午7:59:16
 */
public class HouseKeepingResult<T> {

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
		public HouseKeepingResult() {
			super();
			// TODO Auto-generated constructor stub
		}
		public HouseKeepingResult(int status, T data, String message) {
			super();
			this.status = status;
			this.data = data;
			this.message = message;
		}
		public HouseKeepingResult(int status, String message) {
			super();
			this.status = status;
			this.message = message;
		}
}
