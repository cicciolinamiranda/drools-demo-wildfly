package com.cloudsherpas.droolsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.cloudsherpas.droolsample.repository"})
@EnableConfigurationProperties()
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
