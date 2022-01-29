package com.albo.deserialization.reader;


import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

public interface Reader <T>{
    T read(String fileName) throws FileNotFoundException;

}
