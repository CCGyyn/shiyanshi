package com.zeng.ezsh.wy.entity;

import java.util.Date;

public class FamilyEducateInfo {
    private Integer familyEducateInfoId;

    private Integer ptManagerId;

    private String managerName;

    private Integer ptUserId;
    
    private String parentName;
    
    private String placeProvince;

    private String placeCity;

    private String placeDistrict;

    private String expectSex;

    private String teacherLesson;

    private Integer timesWeek;

    private Short lessonCharge;
    
    private String teacherGrade;
    
    private String linkPhone;

    private Integer findStatus;

    private String otherExplain;
    
    private String addTime;
    
    private Integer delStatus;
    
    public Integer getFamilyEducateInfoId() {
        return familyEducateInfoId;
    }

    public void setFamilyEducateInfoId(Integer familyEducateInfoId) {
        this.familyEducateInfoId = familyEducateInfoId;
    }

    public Integer getPtManagerId() {
        return ptManagerId;
    }

    public void setPtManagerId(Integer ptManagerId) {
        this.ptManagerId = ptManagerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public Integer getPtUserId() {
        return ptUserId;
    }

    public void setPtUserId(Integer ptUserId) {
        this.ptUserId = ptUserId;
    }
    
    public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getPlaceProvince() {
        return placeProvince;
    }

    public void setPlaceProvince(String placeProvince) {
        this.placeProvince = placeProvince == null ? null : placeProvince.trim();
    }

    public String getPlaceCity() {
        return placeCity;
    }

    public void setPlaceCity(String placeCity) {
        this.placeCity = placeCity == null ? null : placeCity.trim();
    }

    public String getPlaceDistrict() {
        return placeDistrict;
    }

    public void setPlaceDistrict(String placeDistrict) {
        this.placeDistrict = placeDistrict == null ? null : placeDistrict.trim();
    }

    public String getExpectSex() {
        return expectSex;
    }

    public void setExpectSex(String expectSex) {
        this.expectSex = expectSex == null ? null : expectSex.trim();
    }

    public String getTeacherLesson() {
        return teacherLesson;
    }

    public void setTeacherLesson(String teacherLesson) {
        this.teacherLesson = teacherLesson == null ? null : teacherLesson.trim();
    }

    public String getTeacherGrade() {
		return teacherGrade;
	}

	public void setTeacherGrade(String teacherGrade) {
		this.teacherGrade = teacherGrade;
	}

	public Integer getTimesWeek() {
		return timesWeek;
	}

	public void setTimesWeek(Integer timesWeek) {
		this.timesWeek = timesWeek;
	}

	public Short getLessonCharge() {
        return lessonCharge;
    }

    public void setLessonCharge(Short lessonCharge) {
        this.lessonCharge = lessonCharge;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone == null ? null : linkPhone.trim();
    }

    public Integer getFindStatus() {
		return findStatus;
	}

	public void setFindStatus(Integer findStatus) {
		this.findStatus = findStatus;
	}

	public String getOtherExplain() {
        return otherExplain;
    }

    public void setOtherExplain(String otherExplain) {
        this.otherExplain = otherExplain == null ? null : otherExplain.trim();
    }

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
    
}