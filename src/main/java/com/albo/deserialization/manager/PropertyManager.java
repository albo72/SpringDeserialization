package com.albo.deserialization.manager;

import com.albo.deserialization.exception.ConfigManagerException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@Component
public class PropertyManager implements ConfigFileManager {

    @Override
    public Map<String, String> getHashMap(String fileName) throws ConfigManagerException {
        return getProperties(fileName).entrySet().stream().collect(
                Collectors.toMap(
                        e -> String.valueOf(e.getKey()),
                        e -> String.valueOf(e.getValue()),
                        (prev, next) -> next, HashMap::new));
    }

    private Properties getProperties(String fileName) throws ConfigManagerException {
        Properties properties = new Properties();
        try(InputStream is = PropertyManager.class.getClassLoader().getResourceAsStream(fileName)){
            if(is == null) {
                throw new ConfigManagerException("Error. Can't load: " + fileName);
            }
            properties.load(is);
            return properties;
        } catch (IOException e){
            throw new ConfigManagerException("Error. Can't load: " + fileName);
        }
    }
}
