package com.zeng.ezsh.wy.entity.Vo;

import java.util.Date;

public class ChargeRecordVoLin {
    private Integer charge_record_id;
    private String manager_name;
    private String user_name;
    private String build_name;
    private String room_floor;
    private String room_num;
    private Integer p_charge_item_id;
    private String charge_item_name;
    private double charge_amount;
    private double unit_price;
    private double total_price;
    private String charge_of_date;
    private String charge_end_date;
    private Byte check_status;
    private Byte status;

    private Integer pt_charge_info_id;
    
    public Integer getPt_charge_info_id() {
		return pt_charge_info_id;
	}

	public void setPt_charge_info_id(Integer pt_charge_info_id) {
		this.pt_charge_info_id = pt_charge_info_id;
	}

	public String getRoom_floor() {
		return room_floor;
	}

	public void setRoom_floor(String room_floor) {
		this.room_floor = room_floor;
	}

	public Integer getCharge_record_id() {
        return charge_record_id;
    }

    public void setCharge_record_id(Integer charge_record_id) {
        this.charge_record_id = charge_record_id;
    }

    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getBuild_name() {
        return build_name;
    }

    public void setBuild_name(String build_name) {
        this.build_name = build_name;
    }

    public String getRoom_num() {
        return room_num;
    }

    public void setRoom_num(String room_num) {
        this.room_num = room_num;
    }

    public Integer getP_charge_item_id() {
        return p_charge_item_id;
    }

    public void setP_charge_item_id(Integer p_charge_item_id) {
        this.p_charge_item_id = p_charge_item_id;
    }

    public String getCharge_item_name() {
        return charge_item_name;
    }

    public void setCharge_item_name(String charge_item_name) {
        this.charge_item_name = charge_item_name;
    }

    public double getCharge_amount() {
        return charge_amount;
    }

    public void setCharge_amount(double charge_amount) {
        this.charge_amount = charge_amount;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getCharge_of_date() {
        return charge_of_date;
    }

    public void setCharge_of_date(String charge_of_date) {
        this.charge_of_date = charge_of_date;
    }

    public String getCharge_end_date() {
        return charge_end_date;
    }

    public void setCharge_end_date(String charge_end_date) {
        this.charge_end_date = charge_end_date;
    }

    public Byte getCheck_status() {
        return check_status;
    }

    public void setCheck_status(Byte check_status) {
        this.check_status = check_status;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "ChargeRecordVoLin [charge_record_id=" + charge_record_id
				+ ", manager_name=" + manager_name + ", user_name=" + user_name
				+ ", build_name=" + build_name + ", room_floor=" + room_floor
				+ ", room_num=" + room_num + ", p_charge_item_id="
				+ p_charge_item_id + ", charge_item_name=" + charge_item_name
				+ ", charge_amount=" + charge_amount + ", unit_price="
				+ unit_price + ", total_price=" + total_price
				+ ", charge_of_date=" + charge_of_date + ", charge_end_date="
				+ charge_end_date + ", check_status=" + check_status
				+ ", status=" + status + ", pt_charge_info_id="
				+ pt_charge_info_id + "]";
	}

  
}
