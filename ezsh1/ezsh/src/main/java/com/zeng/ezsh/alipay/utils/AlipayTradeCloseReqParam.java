package com.zeng.ezsh.alipay.utils;
/**
 * @description 关闭支付宝交易订单请求参数实体类
 *
 * @author qwc
 */
public class AlipayTradeCloseReqParam {
	public String trade_no;

	public String out_trade_no;

	public String operator_id;

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	public String gtString() {
		if (this.operator_id == null) {
			this.operator_id = "ezshSystem";
		}
		return "{" + "\"trade_no\":\"" + this.trade_no + "\","
				+ "\"out_trade_no\":\"" + this.out_trade_no + "\","
				+ "\"operator_id\":\"" + this.operator_id + "\"" + "}";
	}
}
