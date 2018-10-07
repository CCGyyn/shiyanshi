package com.zeng.ezsh.wy.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class HkItem {
	@JsonIgnore
    private Integer id;
	@JsonIgnore
    private int hkId;
	@JsonIgnore
    private String hkName;
	@JsonIgnore
    private int typeId;
	@JsonIgnore
    private String hkType;

    private String item;
  
    private String itemProperty;

    private BigDecimal price;

    private String comment;
    @JsonIgnore
    private String img;
    
    

    public String getHkName() {
		return hkName;
	}

	public void setHkName(String hkName) {
		this.hkName = hkName;
	}

	public String getHkType() {
		return hkType;
	}

	public void setHkType(String hkType) {
		this.hkType = hkType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public String getItemProperty() {
        return itemProperty;
    }

    public void setItemProperty(String itemProperty) {
        this.itemProperty = itemProperty == null ? null : itemProperty.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }
}