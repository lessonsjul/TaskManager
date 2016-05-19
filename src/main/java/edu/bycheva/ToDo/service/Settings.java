package edu.bycheva.ToDo.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static Settings ourInstance = new Settings();
    private final Properties properties = new Properties();

    private Settings() {
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource("db.properties").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Settings getInstance() {
        return ourInstance;
    }

    public String value(String key){
        return this.properties.getProperty(key);
    }
}
