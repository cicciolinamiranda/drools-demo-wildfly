package com.cloudsherpas.droolsample.util;

import com.cloudsherpas.droolsample.api.resource.RuleArtifactResource;
import com.cloudsherpas.droolsample.domain.RuleArtifact;

/**
 * @author CMiranda
 */
public class RuleUtil {

    public static String createPath(String groupId, String artifactId, String version) {
        String groupPath = groupId.replace(".", "/");
        return groupPath
                + "/"
                + artifactId
                + "/"
                + version
                + "/"
                + artifactId + "-" + version + ".jar";
    }

    public static RuleArtifactResource toResource(RuleArtifact ruleArtifact) {
        RuleArtifactResource response = new RuleArtifactResource();

        response.setArtifactId(ruleArtifact.getArtifactId());
        response.setGroupId(ruleArtifact.getGroupId());
        response.setVersion(ruleArtifact.getVersion());
        response.setActive(ruleArtifact.isActive());
        return response;
    }
}