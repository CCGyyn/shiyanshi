package com.zeng.ezsh.wy.entity;

import java.math.BigDecimal;
import java.util.List;

public class ResidentialUser {
	private Integer userId;

    private String userIcon;

    private String userLinkphone;

    private String userAccount;

    private String userPassword;

    private String userName;

    private String userSex;

    private String userNickname;

    private String userSerialNumber;

    private String userIdentityCard;
    
    private Integer userTeacherFeeStatus;

    private String userTeacherFeeValid;
    
	private List<UMsIds> uroomList;

	private UMsIds umsIdsInfo;
	
	private BigDecimal userIntegral;
	
	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon == null ? null : userIcon.trim();
    }

    public String getUserLinkphone() {
        return userLinkphone;
    }

    public void setUserLinkphone(String userLinkphone) {
        this.userLinkphone = userLinkphone == null ? null : userLinkphone.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public String getUserSerialNumber() {
        return userSerialNumber;
    }

    public void setUserSerialNumber(String userSerialNumber) {
        this.userSerialNumber = userSerialNumber == null ? null : userSerialNumber.trim();
    }

    public String getUserIdentityCard() {
        return userIdentityCard;
    }

    public void setUserIdentityCard(String userIdentityCard) {
        this.userIdentityCard = userIdentityCard == null ? null : userIdentityCard.trim();
    }
     
    public Integer getUserTeacherFeeStatus() {
		return userTeacherFeeStatus;
	}

	public void setUserTeacherFeeStatus(Integer userTeacherFeeStatus) {
		this.userTeacherFeeStatus = userTeacherFeeStatus;
	}

	public String getUserTeacherFeeValid() {
		return userTeacherFeeValid;
	}

	public void setUserTeacherFeeValid(String userTeacherFeeValid) {
		this.userTeacherFeeValid = userTeacherFeeValid;
	}

	public List<UMsIds> getUroomList() {
		return uroomList;
	}

	public void setUroomList(List<UMsIds> uroomList) {
		this.uroomList = uroomList;
	}

	public UMsIds getUmsIdsInfo() {
		return umsIdsInfo;
	}

	public void setUmsIdsInfo(UMsIds umsIdsInfo) {
		this.umsIdsInfo = umsIdsInfo;
	}

	public BigDecimal getUserIntegral() {
		return userIntegral;
	}

	public void setUserIntegral(BigDecimal userIntegral) {
		this.userIntegral = userIntegral;
	}
}