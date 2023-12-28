package com.tmc.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mail-config")
public class MailConfiguration {

    private String SMTP_HOST;
    private String SMTP_PORT;
    private String GMAIL_USERNAME;
    private String GMAIL_PASSWORD;

    public MailConfiguration() {

    }

    public String getSMTP_HOST() {
        return SMTP_HOST;
    }

    public void setSMTP_HOST(String sMTP_HOST) {
        SMTP_HOST = sMTP_HOST;
    }

    public String getSMTP_PORT() {
        return SMTP_PORT;
    }

    public void setSMTP_PORT(String sMTP_PORT) {
        SMTP_PORT = sMTP_PORT;
    }

    public String getGMAIL_USERNAME() {
        return GMAIL_USERNAME;
    }

    public void setGMAIL_USERNAME(String gMAIL_USERNAME) {
        GMAIL_USERNAME = gMAIL_USERNAME;
    }

    public String getGMAIL_PASSWORD() {
        return GMAIL_PASSWORD;
    }

    public void setGMAIL_PASSWORD(String gMAIL_PASSWORD) {
        GMAIL_PASSWORD = gMAIL_PASSWORD;
    }
    
}
