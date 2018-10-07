package com.zeng.ezsh.wy.entity;

import java.util.Arrays;
import java.util.Date;

public class Advice {
    private Integer id;

    private String userName;

    private Integer communityId;

    private String userPhone;
    
    private String house;

    private String advice;
    
    private String adviceTitle;
    
    private String place;
    //图片名称
    private String img;
    //图片路径
    private String[] imgUrl;

    private Integer result;
    
    private Date createTime;
    
    private String createtime;
    
    

    public String getCreatetime() {
		return createtime;
	}



	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}



	public String getAdviceTitle() {
		return adviceTitle;
	}



	public void setAdviceTitle(String adviceTitle) {
		this.adviceTitle = adviceTitle;
	}



	public String getPlace() {
		return place;
	}



	public void setPlace(String place) {
		this.place = place;
	}



	public String[] getImgUrl() {
		return imgUrl;
	}



	public void setImgUrl(String[] imgUrl) {
		this.imgUrl = imgUrl;
	}



	public String getImg() {
		return img;
	}



	public void setImg(String img) {
		this.img = img;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public Integer getId() {
        return id;
    }
    
    

    public String getHouse() {
		return house;
	}



	public void setHouse(String house) {
		this.house = house;
	}



	public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice == null ? null : advice.trim();
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

	public Advice() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	



	@Override
	public String toString() {
		return "Advice [id=" + id + ", userName=" + userName + ", communityId="
				+ communityId + ", userPhone=" + userPhone + ", house=" + house
				+ ", advice=" + advice + ", adviceTitle=" + adviceTitle
				+ ", place=" + place + ", img=" + img + ", imgUrl="
				+ Arrays.toString(imgUrl) + ", result=" + result
				+ ", createTime=" + createTime + "]";
	}



	public Advice(String userName, Integer communityId, String userPhone,
			String house, String advice, String adviceTitle, String place, Integer result,String img) {
		super();
		this.userName = userName;
		this.communityId = communityId;
		this.userPhone = userPhone;
		this.house = house;
		this.advice = advice;
		this.adviceTitle = adviceTitle;
		this.place = place;
		this.img = img;
	}
    
    
}