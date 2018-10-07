package com.zeng.ezsh.wy.entity;

public class GridRoomItem {
    private Integer roomGridItemId;

    private Integer ptManagerId;

    private Integer ptBuildId;

    private Integer ptRoomId;

    private String roomNum;

    private Integer ptGridId;

    private String roomGridName;

    public Integer getRoomGridItemId() {
        return roomGridItemId;
    }

    public void setRoomGridItemId(Integer roomGridItemId) {
        this.roomGridItemId = roomGridItemId;
    }

    public Integer getPtManagerId() {
        return ptManagerId;
    }

    public void setPtManagerId(Integer ptManagerId) {
        this.ptManagerId = ptManagerId;
    }

    public Integer getPtBuildId() {
        return ptBuildId;
    }

    public void setPtBuildId(Integer ptBuildId) {
        this.ptBuildId = ptBuildId;
    }

    public Integer getPtRoomId() {
        return ptRoomId;
    }

    public void setPtRoomId(Integer ptRoomId) {
        this.ptRoomId = ptRoomId;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum == null ? null : roomNum.trim();
    }

    public Integer getPtGridId() {
        return ptGridId;
    }

    public void setPtGridId(Integer ptGridId) {
        this.ptGridId = ptGridId;
    }

    public String getRoomGridName() {
        return roomGridName;
    }

    public void setRoomGridName(String roomGridName) {
        this.roomGridName = roomGridName == null ? null : roomGridName.trim();
    }
}