package com.zeng.ezsh.wy.entity;

public class Teacher {
    private Integer teacherId;

    private String teacherName;
    
    private Integer teacherSex;
    
    private String teacherIcon;

    private String teacherNickName;

    private String teacherTelephone;

    private String teacherProvince;

    private String teacherCity;

    private String teacherDistrict;

    private String teacherWcOpenId;

    private String teacherWcUnionId;

    private String identityCardImg;

    private String studentCard;

    private Integer checkStatus;

    private Integer completeStatus;

    private String failureReason;
    
    private TeacherResume resumeInfo;
    
    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }
    
    public Integer getTeacherSex() {
		return teacherSex;
	}

	public void setTeacherSex(Integer teacherSex) {
		this.teacherSex = teacherSex;
	}

	public String getTeacherIcon() {
        return teacherIcon;
    }

    public void setTeacherIcon(String teacherIcon) {
        this.teacherIcon = teacherIcon == null ? null : teacherIcon.trim();
    }

    public String getTeacherNickName() {
        return teacherNickName;
    }

    public void setTeacherNickName(String teacherNickName) {
        this.teacherNickName = teacherNickName == null ? null : teacherNickName.trim();
    }

    public String getTeacherTelephone() {
        return teacherTelephone;
    }

    public void setTeacherTelephone(String teacherTelephone) {
        this.teacherTelephone = teacherTelephone == null ? null : teacherTelephone.trim();
    }

    public String getTeacherProvince() {
        return teacherProvince;
    }

    public void setTeacherProvince(String teacherProvince) {
        this.teacherProvince = teacherProvince == null ? null : teacherProvince.trim();
    }

    public String getTeacherCity() {
        return teacherCity;
    }

    public void setTeacherCity(String teacherCity) {
        this.teacherCity = teacherCity == null ? null : teacherCity.trim();
    }

    public String getTeacherDistrict() {
        return teacherDistrict;
    }

    public void setTeacherDistrict(String teacherDistrict) {
        this.teacherDistrict = teacherDistrict == null ? null : teacherDistrict.trim();
    }

    public String getTeacherWcOpenId() {
        return teacherWcOpenId;
    }

    public void setTeacherWcOpenId(String teacherWcOpenId) {
        this.teacherWcOpenId = teacherWcOpenId == null ? null : teacherWcOpenId.trim();
    }

    public String getTeacherWcUnionId() {
        return teacherWcUnionId;
    }

    public void setTeacherWcUnionId(String teacherWcUnionId) {
        this.teacherWcUnionId = teacherWcUnionId == null ? null : teacherWcUnionId.trim();
    }

    public String getIdentityCardImg() {
        return identityCardImg;
    }

    public void setIdentityCardImg(String identityCardImg) {
        this.identityCardImg = identityCardImg == null ? null : identityCardImg.trim();
    }

    public String getStudentCard() {
        return studentCard;
    }

    public void setStudentCard(String studentCard) {
        this.studentCard = studentCard == null ? null : studentCard.trim();
    }

    public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getCompleteStatus() {
		return completeStatus;
	}

	public void setCompleteStatus(Integer completeStatus) {
		this.completeStatus = completeStatus;
	}

	public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason == null ? null : failureReason.trim();
    }

	public TeacherResume getResumeInfo() {
		return resumeInfo;
	}

	public void setResumeInfo(TeacherResume resumeInfo) {
		this.resumeInfo = resumeInfo;
	}
}