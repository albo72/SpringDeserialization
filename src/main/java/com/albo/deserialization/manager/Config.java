package com.albo.deserialization.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource("classpath:/config.properties")
public class Config {

    @Value("#{'${files}'.split(',')}")
    private List<String> files;

    public Config setFiles(List<String> files) {
        this.files = files;
        return this;
    }

    public List<String> getFiles() {
        return files;
    }
}
