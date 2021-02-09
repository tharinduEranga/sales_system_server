package com.icbt.ap.sales.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author Tharindu Eranga
 * @date Mon 08 Feb 2021
 */
@Configuration
public class AppConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages/exception-message", "classpath:messages/success-message");
        messageSource.setCacheSeconds(60); //reload messages every 60 seconds
        return messageSource;
    }
}
