package com.zeng.ezsh.wy.entity;

import java.util.List;

public class HkType {
    private Integer hkId;

    private Integer typeId;


    private String hkType;
    
    private List<HkItem> hkItemList;
    

    public List<HkItem> getHkItemList() {
		return hkItemList;
	}

	public void setHkItemList(List<HkItem> hkItemList) {
		this.hkItemList = hkItemList;
	}

	public Integer getHkId() {
        return hkId;
    }

    public void setHkId(Integer hkId) {
        this.hkId = hkId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }


    public String getHkType() {
        return hkType;
    }

    public void setHkType(String hkType) {
        this.hkType = hkType == null ? null : hkType.trim();
    }

	public HkType(Integer typeId, String hkType) {
		super();
		this.typeId = typeId;
		this.hkType = hkType;
	}

	public HkType() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}