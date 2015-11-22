package com.cloudsherpas.droolsample.config;

import com.cloudsherpas.droolsample.config.property.RulesProperties;
import com.cloudsherpas.droolsample.domain.RuleArtifact;
import com.cloudsherpas.droolsample.repository.RuleArtifactRepository;
import org.drools.core.io.impl.UrlResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.KieResources;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

import static com.cloudsherpas.droolsample.util.RuleUtil.createPath;

/**
 * @author RMPader
 */
@Configuration
public class RulesConfig {

//    @Autowired
//    private RulesProperties rulesProperties;
//
//    @Autowired
//    private RuleArtifactRepository ruleArtifactRepository;
//
//    @Bean
//    public KieContainer kieContainer() throws IOException {
//        KieServices ks = KieServices.Factory.get();
//        KieRepository repo = ks.getRepository();
//        KieResources resources = ks.getResources();
//
//        RuleArtifact ruleArtifact = ruleArtifactRepository.findByActiveTrue();
//
//        String url;
//        if (ruleArtifact == null) {
//            url = rulesProperties.getRulesRepoPath() + "com/cloudsherpas/rule/1.0/rule-1.0.jar";
//        } else {
//            url = rulesProperties.getRulesRepoPath()
//                    + createPath(ruleArtifact.getGroupId(),
//                                 ruleArtifact.getArtifactId(),
//                                 ruleArtifact.getVersion());
//        }
//
//        UrlResource urlResource = (UrlResource) ks.getResources()
//                                                  .newUrlResource(url);
//        urlResource.setUsername(rulesProperties.getUsername());
//        urlResource.setPassword(rulesProperties.getPassword());
//        urlResource.setBasicAuthentication("enabled");
//        InputStream is = urlResource.getInputStream();
//        KieModule k = repo.addKieModule(resources.newInputStreamResource(is));
//
//        return ks.newKieContainer(k.getReleaseId());
//    }
}