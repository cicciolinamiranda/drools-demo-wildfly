package com.cloudsherpas.droolsample.config;

import java.io.IOException;
import java.io.InputStream;

import org.drools.core.io.impl.UrlResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.KieResources;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudsherpas.droolsample.config.property.RulesProperties;

/**
 * @author RMPader
 */
@Configuration
public class RulesConfig {

    @Autowired
    private RulesProperties rulesProperties;

    @Bean
    public KieContainer kieContainer() throws IOException {
        KieServices ks = KieServices.Factory.get();
        KieRepository repo = ks.getRepository();
        KieResources resources = ks.getResources();

        // TODO: make this configurable at runtime. Include in M2 (Milestone 2).
//        String userpassword = "admin:admin";
        String url = rulesProperties.getRulesRepoPath()
                + "com/cloudsherpas/rule/1.0/rule-1.0.jar";

        // HttpURLConnection httpURLConnection = (HttpURLConnection)new
        // URL(url).openConnection();
        // String authEnc = new Base64Encoder().encode(userpassword.getBytes());
        // httpURLConnection.setRequestProperty("Authorization", "Basic " +
        // authEnc);
        // repo.addKieModule(resources.newInputStreamResource(
        // httpURLConnection.getInputStream()));
        // // ReleaseIdImpl releaseId = new ReleaseIdImpl("demo",
        // "CourseSuggestion", "LATEST");
        // ReleaseIdImpl releaseId = new ReleaseIdImpl("com.cloudsherpas",
        // "rule", "LATEST");
        UrlResource urlResource = (UrlResource) ks.getResources()
                .newUrlResource(url);
        urlResource.setUsername(rulesProperties.getUsername());
        urlResource.setPassword(rulesProperties.getPassword());
        urlResource.setBasicAuthentication("enabled");
        InputStream is = urlResource.getInputStream();
        KieModule k = repo.addKieModule(resources.newInputStreamResource(is));
        return ks.newKieContainer(k.getReleaseId());
    }

}
