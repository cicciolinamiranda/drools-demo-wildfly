package com.cloudsherpas.droolsample.service;

import static com.cloudsherpas.droolsample.util.RuleUtil.createPath;

import java.io.IOException;

import java.io.InputStream;

import org.drools.core.io.impl.UrlResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.KieResources;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudsherpas.droolsample.api.exception.InvalidArtifactException;
import com.cloudsherpas.droolsample.api.resource.ArtifactActivationResource;
import com.cloudsherpas.droolsample.api.resource.RuleArtifactResource;
import com.cloudsherpas.droolsample.config.property.RulesProperties;
import com.cloudsherpas.droolsample.domain.RuleArtifact;
import com.cloudsherpas.droolsample.repository.RuleArtifactRepository;

/**
 * @author RMPader
 */
@Service
public class RulesdminService {

    @Autowired
    private RulesProperties rulesProperties;

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private RuleArtifactRepository ruleArtifactRepository;

    @Transactional
    public void activateRuleArtifact(ArtifactActivationResource artifactActivationResource) throws IOException {
        RuleArtifact ruleArtifact = null;

        Iterable<RuleArtifact> artifacts = ruleArtifactRepository.findAll();

        for (RuleArtifact artifact : artifacts) {
            if (artifact.getId()
                        .equals(artifactActivationResource.getId())) {
                artifact.setActive(true);
                ruleArtifact = artifact;
            } else {
                artifact.setActive(false);
            }
        }
        ruleArtifactRepository.save(artifacts);

        if (ruleArtifact != null) {
            KieServices ks = KieServices.Factory.get();
            KieRepository repo = ks.getRepository();
            KieResources resources = ks.getResources();

            String url = rulesProperties.getRulesRepoPath()
                    + createPath(ruleArtifact.getGroupId(),
                                 ruleArtifact.getArtifactId(),
                                 ruleArtifact.getVersion());

            UrlResource urlResource = (UrlResource) resources
                                                      .newUrlResource(url);
            urlResource.setUsername(rulesProperties.getUsername());
            urlResource.setPassword(rulesProperties.getPassword());
            urlResource.setBasicAuthentication("enabled");
            InputStream is = urlResource.getInputStream();
            KieModule k = repo.addKieModule(resources.newInputStreamResource(is));
            kieContainer.updateToVersion(k.getReleaseId());
        } else {
            throw new InvalidArtifactException();
        }
    }

    public void addRuleArtifact(RuleArtifactResource ruleArtifactResource) {
        RuleArtifact ruleArtifact = new RuleArtifact(ruleArtifactResource.getGroupId(),
                                                     ruleArtifactResource.getArtifactId(),
                                                     ruleArtifactResource.getVersion());

        ruleArtifactRepository.save(ruleArtifact);
    }
}