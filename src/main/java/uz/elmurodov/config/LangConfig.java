package uz.elmurodov.config;




import uz.elmurodov.enums.Language;


import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import static uz.elmurodov.FreePdfBot.users;

/**
 * @author Elmurodov Javohir, Thu 3:15 PM. 12/9/2021
 */
public class LangConfig {
    private static Properties p;
    public static Properties uz;
    public static Properties ru;
    public static Properties en;
    public static String pathPre = "src/main/resources/i18n/";

    static {load();}
    private static void load() {
        try (FileReader uzFileReader = new FileReader(pathPre + "uz.properties");
             FileReader ruFileReader = new FileReader(pathPre + "ru.properties");
             FileReader enFileReader = new FileReader(pathPre + "en.properties");
             FileReader pFileReder = new FileReader("src/main/resources/app.properties")) {
            uz = new Properties();
            ru = new Properties();
            en = new Properties();
            p = new Properties();
            uz.load(uzFileReader);
            ru.load(ruFileReader);
            en.load(enFileReader);
            p.load(pFileReder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return p.getProperty(key);
    }

    public static String get(String chatId, String key) {
        if(Objects.isNull(users.get(chatId).getLanguage())){
            return p.getProperty(key);
        }
        if (users.get(chatId).getLanguage().equals("en")) {
            return en.getProperty(key);
        } else if (users.get(chatId).getLanguage().equals("uz")) {
            return  uz.getProperty(key);
        } else{
            return ru.getProperty(key);
        }
    }
//    public static String get(String key, Language language) {
//        String langCode = language.getCode();
//        if (langCode.equalsIgnoreCase("uz"))
//            return uz.getProperty(key);
//        if (langCode.equalsIgnoreCase("ru"))
//            return ru.getProperty(key);
//        return en.getProperty(key);
//    }
}
