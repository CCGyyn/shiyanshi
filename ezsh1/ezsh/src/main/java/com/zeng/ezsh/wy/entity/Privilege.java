package com.zeng.ezsh.wy.entity;

public class Privilege {
	private String privId;

    private String privModule;

    private String operation;

    private String privDescribe;

    private String pPrivId;

    public String getPrivId() {
        return privId;
    }

    public void setPrivId(String privId) {
        this.privId = privId == null ? null : privId.trim();
    }

    public String getPrivModule() {
        return privModule;
    }

    public void setPrivModule(String privModule) {
        this.privModule = privModule == null ? null : privModule.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getPrivDescribe() {
        return privDescribe;
    }

    public void setPrivDescribe(String privDescribe) {
        this.privDescribe = privDescribe == null ? null : privDescribe.trim();
    }

    public String getpPrivId() {
        return pPrivId;
    }

    public void setpPrivId(String pPrivId) {
        this.pPrivId = pPrivId == null ? null : pPrivId.trim();
    }
}