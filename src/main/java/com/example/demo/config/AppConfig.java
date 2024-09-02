package com.example.demo.config;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public GoogleAuthenticator getGoogleAuthenticator() {
        return new GoogleAuthenticator();
    }

}
