package com.cloudsherpas.droolsample.api.resource;

import java.util.ArrayList;
import java.util.List;

public class DroolsRuleVersionDTOList {

    private List<DroolsRuleVersionDTO> droolsRuleVersionList;

    public DroolsRuleVersionDTOList() {
        this.droolsRuleVersionList = new ArrayList<DroolsRuleVersionDTO>();
    }

    public List<DroolsRuleVersionDTO> getDroolsRuleVersionList() {
        return droolsRuleVersionList;
    }

    public void setDroolsRuleVersionList(
            final List<DroolsRuleVersionDTO> droolsRuleVersionList) {
        this.droolsRuleVersionList = droolsRuleVersionList;
    }

    public void addDroolsRulesVersion(
            final DroolsRuleVersionDTO droolsRuleVersion) {
        droolsRuleVersionList.add(droolsRuleVersion);
    }
}
