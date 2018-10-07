package com.zeng.ezsh.wy.entity;

public class CustomerFamilyMember {
    private Integer familyMemberId;

    private Integer ptManagerId;

    private Integer ptRoomId;

    private Integer ptCustomerId;

    private String familyMemberName;

    private String familyMemberSex;

    private String familyMemberIdCard;

    private String familyMemberTelephone;

    private Byte familyMemberResideClassify;

    private String familyMemberRelationship;

    public Integer getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(Integer familyMemberId) {
        this.familyMemberId = familyMemberId;
    }

    public Integer getPtManagerId() {
        return ptManagerId;
    }

    public void setPtManagerId(Integer ptManagerId) {
        this.ptManagerId = ptManagerId;
    }

    public Integer getPtRoomId() {
        return ptRoomId;
    }

    public void setPtRoomId(Integer ptRoomId) {
        this.ptRoomId = ptRoomId;
    }

    public Integer getPtCustomerId() {
        return ptCustomerId;
    }

    public void setPtCustomerId(Integer ptCustomerId) {
        this.ptCustomerId = ptCustomerId;
    }

    public String getFamilyMemberName() {
        return familyMemberName;
    }

    public void setFamilyMemberName(String familyMemberName) {
        this.familyMemberName = familyMemberName == null ? null : familyMemberName.trim();
    }

    public String getFamilyMemberSex() {
        return familyMemberSex;
    }

    public void setFamilyMemberSex(String familyMemberSex) {
        this.familyMemberSex = familyMemberSex == null ? null : familyMemberSex.trim();
    }

    public String getFamilyMemberIdCard() {
        return familyMemberIdCard;
    }

    public void setFamilyMemberIdCard(String familyMemberIdCard) {
        this.familyMemberIdCard = familyMemberIdCard == null ? null : familyMemberIdCard.trim();
    }

    public String getFamilyMemberTelephone() {
        return familyMemberTelephone;
    }

    public void setFamilyMemberTelephone(String familyMemberTelephone) {
        this.familyMemberTelephone = familyMemberTelephone == null ? null : familyMemberTelephone.trim();
    }

    public Byte getFamilyMemberResideClassify() {
        return familyMemberResideClassify;
    }

    public void setFamilyMemberResideClassify(Byte familyMemberResideClassify) {
        this.familyMemberResideClassify = familyMemberResideClassify;
    }

    public String getFamilyMemberRelationship() {
        return familyMemberRelationship;
    }

    public void setFamilyMemberRelationship(String familyMemberRelationship) {
        this.familyMemberRelationship = familyMemberRelationship == null ? null : familyMemberRelationship.trim();
    }
}