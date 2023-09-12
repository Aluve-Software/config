package tech.aluvesoftware.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private final Properties properties;

    // This is the constructor of the `tech.aluvesoftware.config.ConfigFileReader` class. It reads a configuration file based on the
// value of the system property "env" (which should be set to the environment name, e.g. "local",
// "dev", "prod", etc.), and loads the properties from that file into a `Properties` object. If the
// file is not found, it throws a `RuntimeException` with an error message.
    public ConfigFileReader() {
        BufferedReader reader;
        try {

            reader = new BufferedReader(new FileReader("properties//Configuration."+System.getProperty("env").toLowerCase()+".properties"));

            System.out.println(reader);
            this.properties = new Properties();

        } catch (FileNotFoundException var4) {
            var4.printStackTrace();
            throw new RuntimeException("Configuration.local.properties not found at properties//Configuration."+System.getProperty("env").toLowerCase()+".properties");
        }

        try {
            this.properties.load(reader);
            reader.close();
        } catch (IOException var3) {
            var3.printStackTrace();
        }
    }

    /**
     * This function retrieves a configuration value from a properties file and throws an exception if the
     * value is not found.
     *
     * @param name A string representing the name of the configuration property to retrieve.
     * @return The method returns a String value, which is the value of the property with the specified
     * name in the Configuration file. If the property is not found, a RuntimeException is thrown with an
     * error message indicating that the property is not specified in the Configuration file.
     */
    public String getConfig(String name) {
        String url = this.properties.getProperty(name);
        if (url != null) {
            return url;
        } else {
            throw new RuntimeException(name + " not specified in the Configuration."+System.getProperty("env").toLowerCase()+".properties file.");
        }
    }


}
