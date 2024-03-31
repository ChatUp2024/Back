package com.chatup.back.config;

import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ConfigEnv {

    private final Environment environment;

    private final String testApi;
    private final String frontUrlLocal;
    private final String frontUrlDomain;

    public ConfigEnv(Environment environment) {
        this.environment = environment;
        testApi = environment.getProperty("TEST_API");
        frontUrlLocal = environment.getProperty("FRONT_URL_LOCAL");
        frontUrlDomain = environment.getProperty("FRONT_URL_DOMAIN");
    }
}
