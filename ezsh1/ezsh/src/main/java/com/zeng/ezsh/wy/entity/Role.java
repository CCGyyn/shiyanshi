package com.zeng.ezsh.wy.entity;

public class Role {
    private Integer roId;
    
    private Integer ptManagerId;
    
    private String roName;

    private String roDescr;

    private String roPrevNames;

    public Integer getRoId() {
        return roId;
    }

    public void setRoId(Integer roId) {
        this.roId = roId;
    }
    
    public Integer getPtManagerId() {
		return ptManagerId;
	}

	public void setPtManagerId(Integer ptManagerId) {
		this.ptManagerId = ptManagerId;
	}

	public String getRoName() {
        return roName;
    }

    public void setRoName(String roName) {
        this.roName = roName == null ? null : roName.trim();
    }

    public String getRoDescr() {
        return roDescr;
    }

    public void setRoDescr(String roDescr) {
        this.roDescr = roDescr == null ? null : roDescr.trim();
    }

    public String getRoPrevNames() {
        return roPrevNames;
    }

    public void setRoPrevNames(String roPrevNames) {
        this.roPrevNames = roPrevNames == null ? null : roPrevNames.trim();
    }
}