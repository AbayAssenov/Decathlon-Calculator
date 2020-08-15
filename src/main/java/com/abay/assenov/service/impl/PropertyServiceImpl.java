package com.abay.assenov.service.impl;

import com.abay.assenov.service.PropertyService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertyServiceImpl implements PropertyService {

    private Properties properties;

    public PropertyServiceImpl(Properties properties) {
        this.properties = properties;
    }

    public PropertyServiceImpl() {
    }

    @Override
    public String getPropertyValue(String propertyName, String valueName) {
        if (Objects.isNull(properties)) { // Upload default property file
            try (InputStream input = PropertyServiceImpl.class.getClassLoader().getResourceAsStream(propertyName)) {
                properties = new Properties();
                //load a properties file from class path
                properties.load(input);
            } catch (IOException ex) {
                System.err.format("IOException: %s%n", ex);
            }
        }

        return properties.getProperty(valueName);
    }
}
