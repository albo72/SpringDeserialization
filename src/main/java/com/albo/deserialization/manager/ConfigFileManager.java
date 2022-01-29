package com.albo.deserialization.manager;

import com.albo.deserialization.exception.ConfigManagerException;

import java.util.Map;

public interface ConfigFileManager {
    Map<String, String> getHashMap(String fileName) throws ConfigManagerException;

}
