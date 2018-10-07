package com.zeng.ezsh.wy.entity;

public class GoodsDistribution {
    private Integer gDistributionId;

    private String gDistributionName;

    public Integer getgDistributionId() {
        return gDistributionId;
    }

    public void setgDistributionId(Integer gDistributionId) {
        this.gDistributionId = gDistributionId;
    }

    public String getgDistributionName() {
        return gDistributionName;
    }

    public void setgDistributionName(String gDistributionName) {
        this.gDistributionName = gDistributionName == null ? null : gDistributionName.trim();
    }
}