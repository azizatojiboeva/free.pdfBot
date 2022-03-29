package uz.elmurodov.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.elmurodov.config.LangConfig;
import uz.elmurodov.emojis.Emojis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Elmurodov Javohir, Fri 12:09 PM. 12/17/2021
 */
public class InlineBoards {
    private static final InlineKeyboardMarkup board = new InlineKeyboardMarkup();


    public static InlineKeyboardMarkup languageButtons(String... caption) {
        InlineKeyboardButton uz = new InlineKeyboardButton(Emojis.UZ + " Uzbek");
        uz.setCallbackData("uz");

        InlineKeyboardButton ru = new InlineKeyboardButton(Emojis.RU + " Russian");
        ru.setCallbackData("ru");

        InlineKeyboardButton en = new InlineKeyboardButton(Emojis.EN + " English");
        en.setCallbackData("en");
        board.setKeyboard(Arrays.asList(getRow(uz), getRow(ru), getRow(en)));
        return board;
    }

    public static ReplyKeyboard gender(String chatId) {
        InlineKeyboardButton male = new InlineKeyboardButton(Emojis.MALE + LangConfig.get(chatId, "male"));
        male.setCallbackData("male");

        InlineKeyboardButton female = new InlineKeyboardButton(Emojis.FEMALE + LangConfig.get(chatId, "female"));
        female.setCallbackData("female");

        board.setKeyboard(Arrays.asList(getRow(male), getRow(female)));
        return board;
    }


    private static List<InlineKeyboardButton> getRow(InlineKeyboardButton... buttons) {
        return Arrays.stream(buttons).toList();
    }

    public static ReplyKeyboard donate(String chatId) {
        InlineKeyboardButton keyboardButton = new InlineKeyboardButton(LangConfig.get(chatId, "donate"));
        keyboardButton.setCallbackData("donate");
        board.setKeyboard(Arrays.asList(getRow(keyboardButton)));
        return board;
    }
//    public static ReplyKeyboard next(String chatId) {
//        InlineKeyboardButton keyboardButton = new InlineKeyboardButton(LangConfig.get(chatId,"next"));
//        keyboardButton.setCallbackData("next");
//        board.setKeyboard(Arrays.asList(getRow(keyboardButton)));
//        return board;
//    }
//    public static ReplyKeyboard prev(String chatId) {
//        InlineKeyboardButton keyboardButton = new InlineKeyboardButton(LangConfig.get(chatId,"prev"));
//        keyboardButton.setCallbackData("prev");
//        board.setKeyboard(Arrays.asList(getRow(keyboardButton)));
//        return board;
//    }


    public static ReplyKeyboard category(String chatId) {
        InlineKeyboardButton row1 = new InlineKeyboardButton(Emojis.SCIENCE + LangConfig.get(chatId, "science"));
        row1.setCallbackData("row1");

        InlineKeyboardButton row2 = new InlineKeyboardButton(Emojis.ADVENTURE + LangConfig.get(chatId, "adventure"));
        row2.setCallbackData("row2");

        InlineKeyboardButton row3 = new InlineKeyboardButton(Emojis.HISTORY + LangConfig.get(chatId, "history"));
        row3.setCallbackData("row3");

        InlineKeyboardButton row4 = new InlineKeyboardButton(Emojis.ROMANCE + LangConfig.get(chatId, "romance"));
        row4.setCallbackData("row4");

        InlineKeyboardButton row5 = new InlineKeyboardButton(Emojis.FANTASY + LangConfig.get(chatId, "fantasy"));
        row5.setCallbackData("row5");

        InlineKeyboardButton row6 = new InlineKeyboardButton(Emojis.DETECTIVE + LangConfig.get(chatId, "detective"));
        row6.setCallbackData("row6");

        board.setKeyboard(Arrays.asList(getRow(row1), getRow(row2), getRow(row3), getRow(row4), getRow(row5), getRow(row6)));
        return board;
    }

    public static ReplyKeyboard settings(String chatId) {

        InlineKeyboardButton button1 = new InlineKeyboardButton("FullName");
        button1.setCallbackData("button1");

        InlineKeyboardButton button2 = new InlineKeyboardButton("Age");
        button2.setCallbackData("button2");

        InlineKeyboardButton button3 = new InlineKeyboardButton("Language");
        button3.setCallbackData("button3");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row1.add(button1);
        row1.add(button2);
        row2.add(button3);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);
        board.setKeyboard(rowList);
        return board;
    }
}
