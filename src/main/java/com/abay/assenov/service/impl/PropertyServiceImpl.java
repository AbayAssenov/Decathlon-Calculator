package com.abay.assenov.service.impl;

import com.abay.assenov.service.PropertyService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.abay.assenov.constant.Contstans.EMPTY_STRING;

public class PropertyServiceImpl implements PropertyService {

    @Override
    public String getPropertyValue(String propertyName, String valueName) {
        try (InputStream input = PropertyServiceImpl.class.getClassLoader().getResourceAsStream(propertyName)) {
            Properties properties = new Properties();
            //load a properties file from class path
            properties.load(input);
            return properties.getProperty(valueName);

        } catch (IOException ex) {
            System.err.format("IOException: %s%n", ex);
        }
        return EMPTY_STRING;
    }
}
