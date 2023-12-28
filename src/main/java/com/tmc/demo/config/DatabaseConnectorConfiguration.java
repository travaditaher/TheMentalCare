package com.tmc.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "db-connector")
public class DatabaseConnectorConfiguration {

    private String JDBC_URL;
    private String DB_URL;
    private String username;
    private String password;

    public DatabaseConnectorConfiguration() {
        
    }

    public String getJDBC_URL() {
        return JDBC_URL;
    }

    public void setJDBC_URL(String jDBC_URL) {
        JDBC_URL = jDBC_URL;
    }

    public String getDB_URL() {
        return DB_URL;
    }

    public void setDB_URL(String dB_URL) {
        DB_URL = dB_URL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
