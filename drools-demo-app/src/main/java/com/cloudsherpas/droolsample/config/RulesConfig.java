package com.cloudsherpas.droolsample.config;

import com.thoughtworks.xstream.core.util.Base64Encoder;
import org.drools.compiler.kproject.ReleaseIdImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.KieResources;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author RMPader
 */
@Configuration
public class RulesConfig {

    @Bean
    public KieContainer kieContainer() throws IOException {
        KieServices ks = KieServices.Factory.get();
        KieRepository repo = ks.getRepository();
        KieResources resources = ks.getResources();

        //TODO: make this configurable at runtime. Include in M2 (Milestone 2).
        String userpassword = "admin:admin";
        String url = "http://localhost:8080/kie-drools-wb-6.3.0.Final-wildfly8/maven2/demo/CourseSuggestion/1.0/CourseSuggestion-1.0.jar";
        HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(url).openConnection();
        String authEnc = new Base64Encoder().encode(userpassword.getBytes());
        httpURLConnection.setRequestProperty("Authorization", "Basic " + authEnc);
        repo.addKieModule(resources.newInputStreamResource( httpURLConnection.getInputStream()));
        ReleaseIdImpl releaseId = new ReleaseIdImpl("demo", "CourseSuggestion", "LATEST");
        return ks.newKieContainer(releaseId);
    }
}
