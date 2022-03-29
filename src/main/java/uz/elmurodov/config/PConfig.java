package uz.elmurodov.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Elmurodov Javohir, Tue 3:02 PM. 12/14/2021
 */
public class PConfig {
    private static Properties p;

    static {
        try (FileReader fileReader = new FileReader("src/main/resources/application.properties")) {
            p = new Properties();
            p.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return p.getProperty(key);
    }


}
