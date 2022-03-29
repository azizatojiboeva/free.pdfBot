package uz.elmurodov.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Elmurodov Javohir, Fri 5:13 PM. 12/17/2021
 */
public class Util {
    public static String asString(Object obj) {
        return gson().toJson(obj);
    }


    public static Gson gsonWithNulls() {
        return new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    }


    public static Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }


}
