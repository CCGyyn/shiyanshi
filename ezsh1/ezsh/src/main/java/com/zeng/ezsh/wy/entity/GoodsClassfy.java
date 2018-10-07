package com.zeng.ezsh.wy.entity;

public class GoodsClassfy {
    private String classfyId;

    private String classfyName;

    private String pClassfyId;

    public String getClassfyId() {
        return classfyId;
    }

    public void setClassfyId(String classfyId) {
        this.classfyId = classfyId == null ? null : classfyId.trim();
    }

    public String getClassfyName() {
        return classfyName;
    }

    public void setClassfyName(String classfyName) {
        this.classfyName = classfyName == null ? null : classfyName.trim();
    }

    public String getpClassfyId() {
        return pClassfyId;
    }

    public void setpClassfyId(String pClassfyId) {
        this.pClassfyId = pClassfyId == null ? null : pClassfyId.trim();
    }
}