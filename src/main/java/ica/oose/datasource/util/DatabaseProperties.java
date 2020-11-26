package ica.oose.datasource.util;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kevin on 23-3-2017.
 */
public class DatabaseProperties {
    private Logger logger = Logger.getLogger(getClass().getName());
    private Properties properties;

    public DatabaseProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't access property file database.properties", e);
        }
    }

    public String driver()
    {
        return properties.getProperty("driver");
    }

    public String connectionString()
    {
        return properties.getProperty("connectionString");
    }
    public String user()
    {
        return properties.getProperty("user");
    }
    public String password()
    {
        return properties.getProperty("password");
    }
}
