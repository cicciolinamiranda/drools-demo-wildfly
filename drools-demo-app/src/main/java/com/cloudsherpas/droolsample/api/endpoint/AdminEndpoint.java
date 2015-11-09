package com.cloudsherpas.droolsample.api.endpoint;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudsherpas.droolsample.api.resource.ArtifactActivationResource;
import com.cloudsherpas.droolsample.api.resource.RuleArtifactResource;
import com.cloudsherpas.droolsample.service.RulesdminService;

/**
 * @author CMiranda
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminEndpoint {

    @Autowired
    private RulesdminService ruleAdminService;

    @RequestMapping(value = "/rules/activate", method = RequestMethod.POST)
    public void activateRuleArtifact(
            @RequestBody ArtifactActivationResource artifactActivationResource)
            throws IOException {
        ruleAdminService.activateRuleArtifact(artifactActivationResource);
    }

    @RequestMapping(value = "/rules/add", method = RequestMethod.POST)
    public void addRuleArtifact(
            @RequestBody RuleArtifactResource ruleArtifactResource)
            throws IOException {
        ruleAdminService.addRuleArtifact(ruleArtifactResource);
    }

}