package com.zeng.ezsh.wy.entity;

public class BenefitApplyWithBLOBs extends BenefitApply {
    private String accidentExplain;

    private String failureReason;

    public String getAccidentExplain() {
        return accidentExplain;
    }

    public void setAccidentExplain(String accidentExplain) {
        this.accidentExplain = accidentExplain == null ? null : accidentExplain.trim();
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason == null ? null : failureReason.trim();
    }
}