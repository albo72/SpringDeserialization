package com.albo.deserialization.util;

import com.albo.deserialization.exception.PropertyManagerException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@Component
public class PropertyManager {

    private Properties getProperties(String fileName) throws PropertyManagerException {
        Properties properties = new Properties();
        try(InputStream is = PropertyManager.class.getClassLoader().getResourceAsStream(fileName)){
            if(is == null) {
                throw new PropertyManagerException("Error. Can't load: " + fileName);
            }
            properties.load(is);
            return properties;
        } catch (IOException e){
            throw new PropertyManagerException("Error. Can't load: " + fileName);
        }
    }

    public Map<String, String> getHashMapFromProperties(String fileName) throws PropertyManagerException {
        return getProperties(fileName).entrySet().stream().collect(
                Collectors.toMap(
                        e -> String.valueOf(e.getKey()),
                        e -> String.valueOf(e.getValue()),
                        (prev, next) -> next, HashMap::new));
    }
}
