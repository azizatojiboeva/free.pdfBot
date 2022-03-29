package uz.elmurodov.config;

import uz.elmurodov.enums.state.AllState;
import uz.elmurodov.enums.state.BookState;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Elmurodov Javohir, Sat 2:59 PM. 12/18/2021
 */
public class State {
    public static final Map<String, AllState> allStateMap = new HashMap<>();
    public static final Map<String, BookState> bookStateMap = new HashMap<>();
    public static final State instance=new State();

    public  static void setAllState(String chatId, AllState state) {
        allStateMap.put(chatId, state);
    }

    public  static void setBookState(String chatId, BookState state) {
        bookStateMap.put(chatId, state);
    }
    public static AllState getAllState(String chatId) {
        return allStateMap.get(chatId);
    }

    public static State getInstance() {
        return instance;
    }
}
