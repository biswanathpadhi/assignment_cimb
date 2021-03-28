package com.cimb.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;
    private Logger logger;

    /**
     * This method is used to load the properties from config.properties file
     *
     * @return it returns Properties prop object
     */
    public Properties init_prop() {

      String propertiesFileName = "config.properties";

        // To initiate the prop
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config/" + propertiesFileName)) {
            logger = LogManager.getLogger();
            properties = new Properties();
            properties.load(in);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;

    }

    public Properties getProperties() {
        return this.properties;
    }
}


