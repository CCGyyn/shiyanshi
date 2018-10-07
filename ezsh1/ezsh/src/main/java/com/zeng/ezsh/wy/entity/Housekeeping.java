package com.zeng.ezsh.wy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Housekeeping {
    private Integer hkId;
    
    private int communityId;

    private String hkName;

    private String hkLeader;

    private String hkPhone;

    //private String hkType;

    private Integer hkStaff;

    //private String hkDescribe;
    
    //private String hkCost;

    private String introduction;
    @JsonIgnore
    private String img;
    
    private String[] imgUrl;
    

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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getHkId() {
        return hkId;
    }

    public void setHkId(Integer hkId) {
        this.hkId = hkId;
    }
    
    

    public int getCommunityId() {
		return communityId;
	}

	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}

	public String getHkName() {
        return hkName;
    }

    public void setHkName(String hkName) {
        this.hkName = hkName == null ? null : hkName.trim();
    }

    public String getHkLeader() {
        return hkLeader;
    }

    public void setHkLeader(String hkLeader) {
        this.hkLeader = hkLeader == null ? null : hkLeader.trim();
    }

    public String getHkPhone() {
        return hkPhone;
    }

    public void setHkPhone(String hkPhone) {
        this.hkPhone = hkPhone;
    }

//    public String getHkType() {
//        return hkType;
//    }
//
//    public void setHkType(String hkType) {
//        this.hkType = hkType == null ? null : hkType.trim();
//    }

    public Integer getHkStaff() {
        return hkStaff;
    }

    public void setHkStaff(Integer hkStaff) {
        this.hkStaff = hkStaff;
    }

//    public String getHkDescribe() {
//        return hkDescribe;
//    }
//
//    public void setHkDescribe(String hkDescribe) {
//        this.hkDescribe = hkDescribe == null ? null : hkDescribe.trim();
//    }

   

	

	public Housekeeping() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Housekeeping [hkId=" + hkId + ", communityId=" + communityId
				+ ", hkName=" + hkName + ", hkLeader=" + hkLeader
				+ ", hkPhone=" + hkPhone + ", hkStaff=" + hkStaff
				+ ", introduction=" + introduction + "]";
	}

	public Housekeeping(int communityId, String hkName, String hkLeader,
			String hkPhone, Integer hkStaff, String introduction) {
		super();
		this.communityId = communityId;
		this.hkName = hkName;
		this.hkLeader = hkLeader;
		this.hkPhone = hkPhone;
		this.hkStaff = hkStaff;
		this.introduction = introduction;
	}

	

	

	

	
    
}