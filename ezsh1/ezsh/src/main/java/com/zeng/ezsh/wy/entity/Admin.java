package com.zeng.ezsh.wy.entity;

public class Admin {
    private Integer adId;

    private String adName;

    private String adAccount;

    private String adPassword;

    private String adTelephone;

    private Integer adManagerId;

    private Integer adRoleId;

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName == null ? null : adName.trim();
    }

    public String getAdAccount() {
        return adAccount;
    }

    public void setAdAccount(String adAccount) {
        this.adAccount = adAccount == null ? null : adAccount.trim();
    }

    public String getAdPassword() {
        return adPassword;
    }

    public void setAdPassword(String adPassword) {
        this.adPassword = adPassword == null ? null : adPassword.trim();
    }

    public String getAdTelephone() {
        return adTelephone;
    }

    public void setAdTelephone(String adTelephone) {
        this.adTelephone = adTelephone == null ? null : adTelephone.trim();
    }

    public Integer getAdManagerId() {
		return adManagerId;
	}

	public void setAdManagerId(Integer adManagerId) {
		this.adManagerId = adManagerId;
	}

	public Integer getAdRoleId() {
        return adRoleId;
    }

    public void setAdRoleId(Integer adRoleId) {
        this.adRoleId = adRoleId;
    }
}