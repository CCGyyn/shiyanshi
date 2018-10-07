package com.zeng.ezsh.wy.entity;

public class VisitorRecord {
    private Integer visitorRecordId;

    private Integer ptCodeId;

    private String visitoreTime;
    
    private VisitorCode visitorCode;
    
    public Integer getVisitorRecordId() {
        return visitorRecordId;
    }

    public void setVisitorRecordId(Integer visitorRecordId) {
        this.visitorRecordId = visitorRecordId;
    }

    public Integer getPtCodeId() {
        return ptCodeId;
    }

    public void setPtCodeId(Integer ptCodeId) {
        this.ptCodeId = ptCodeId;
    }

    public String getVisitoreTime() {
        return visitoreTime;
    }

    public void setVisitoreTime(String visitoreTime) {
        this.visitoreTime = visitoreTime == null ? null : visitoreTime.trim();
    }

	public VisitorCode getVisitorCode() {
		return visitorCode;
	}

	public void setVisitorCode(VisitorCode visitorCode) {
		this.visitorCode = visitorCode;
	}

}