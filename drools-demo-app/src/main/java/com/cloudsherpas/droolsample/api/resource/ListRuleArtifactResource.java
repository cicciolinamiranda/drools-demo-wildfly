package com.cloudsherpas.droolsample.api.resource;

import java.util.ArrayList;
import java.util.List;

public class ListRuleArtifactResource {

    private List<RuleArtifactResource> listRuleArtifactResource;

    public ListRuleArtifactResource() {
        this.listRuleArtifactResource = new ArrayList<RuleArtifactResource>();
    }

    public List<RuleArtifactResource> getListRuleArtifactResource() {
        return listRuleArtifactResource;
    }

    public void setListRuleArtifactResource(
            final List<RuleArtifactResource> listRuleArtifactResource) {
        this.listRuleArtifactResource = listRuleArtifactResource;
    }

    public void addListRuleArtifactResource (final RuleArtifactResource ruleArtifactResource) {
        listRuleArtifactResource.add(ruleArtifactResource);
    }

}
