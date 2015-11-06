package com.cloudsherpas.droolsample.api.resource;

import java.util.ArrayList;
import java.util.List;

public class DroolsRuleVersionDTO {

    private String packageName;
    private List<RuleVersionDTO> ruleVersionDTOList;
    public DroolsRuleVersionDTO() {
        this.ruleVersionDTOList = new ArrayList<RuleVersionDTO>();
    }

    public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    

    public List<RuleVersionDTO> getRuleVersionDTOList() {
        return ruleVersionDTOList;
    }

    public void setRuleVersionDTOList(List<RuleVersionDTO> ruleVersionDTOList) {
        this.ruleVersionDTOList = ruleVersionDTOList;
    }

    public void addRuleVersionDTO(RuleVersionDTO ruleVersionDTO) {
        ruleVersionDTOList.add(ruleVersionDTO);
    }
}
