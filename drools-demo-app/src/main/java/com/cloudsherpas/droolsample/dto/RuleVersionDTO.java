package com.cloudsherpas.droolsample.dto;

public class RuleVersionDTO {
    private String versionName;
    private boolean isDefault;
    public String getVersionName() {
        return versionName;
    }
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
    public boolean isDefault() {
        return isDefault;
    }
    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

}
