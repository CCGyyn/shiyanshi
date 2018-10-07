package com.zeng.ezsh.wy.entity;

import java.math.BigDecimal;

public class TeacherResume {
    private Integer teacherResumeId;

    private Integer ptTeacherId;

    private String placeSchool;

    private String workProvince;

    private String workCity;

    private String expectCourse;

    private BigDecimal expectSalary;
    
    private String timeBucket;
    
    private String otherExplain;
    
    private Teacher teacherInfo;
    
    public Integer getTeacherResumeId() {
        return teacherResumeId;
    }

    public void setTeacherResumeId(Integer teacherResumeId) {
        this.teacherResumeId = teacherResumeId;
    }

    public Integer getPtTeacherId() {
        return ptTeacherId;
    }

    public void setPtTeacherId(Integer ptTeacherId) {
        this.ptTeacherId = ptTeacherId;
    }

    public String getPlaceSchool() {
        return placeSchool;
    }

    public void setPlaceSchool(String placeSchool) {
        this.placeSchool = placeSchool == null ? null : placeSchool.trim();
    }

    public String getWorkProvince() {
        return workProvince;
    }

    public void setWorkProvince(String workProvince) {
        this.workProvince = workProvince == null ? null : workProvince.trim();
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity == null ? null : workCity.trim();
    }

    public String getExpectCourse() {
        return expectCourse;
    }

    public void setExpectCourse(String expectCourse) {
        this.expectCourse = expectCourse == null ? null : expectCourse.trim();
    }

    public BigDecimal getExpectSalary() {
		return expectSalary;
	}

	public void setExpectSalary(BigDecimal expectSalary) {
		this.expectSalary = expectSalary;
	}

	public String getTimeBucket() {
		return timeBucket;
	}

	public void setTimeBucket(String timeBucket) {
		this.timeBucket = timeBucket;
	}

	public String getOtherExplain() {
        return otherExplain;
    }

    public void setOtherExplain(String otherExplain) {
        this.otherExplain = otherExplain == null ? null : otherExplain.trim();
    }

	public Teacher getTeacherInfo() {
		return teacherInfo;
	}

	public void setTeacherInfo(Teacher teacherInfo) {
		this.teacherInfo = teacherInfo;
	}
}