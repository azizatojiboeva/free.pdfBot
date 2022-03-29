package uz.elmurodov.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.elmurodov.FreePdfBot;
import uz.elmurodov.config.LangConfig;
import uz.elmurodov.emojis.Emojis;


import java.util.List;


/**
 * @author Elmurodov Javohir, Fri 12:09 PM. 12/17/2021
 */
public class MarkupBoard {

    private static final ReplyKeyboardMarkup board = new ReplyKeyboardMarkup();


    public static ReplyKeyboardMarkup sharePhoneNumber(String chatId) {
        KeyboardButton phoneContact = new KeyboardButton(Emojis.PHONE + LangConfig.get(chatId, "share.phone.number"));
        phoneContact.setRequestContact(true);
        board.setResizeKeyboard(true);
        board.setSelective(true);
        KeyboardRow row = new KeyboardRow();
        row.add(phoneContact);
        board.setKeyboard(List.of(row));
        return board;

    }

    public static ReplyKeyboardMarkup mainMenu(String chatId) {

        //row1
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(Emojis.SEARCH+LangConfig.get(chatId, "search")));
        row1.add(new KeyboardButton(Emojis.ADD+LangConfig.get(chatId, "put")));
        //row2
        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(Emojis.MY_BOOKS+LangConfig.get(chatId, "my.books")));
        row2.add(new KeyboardButton(Emojis.CONTACT_US+LangConfig.get(chatId, "contact.us")));
   //     row2.add(new KeyboardButton(Emojis.STATISTIC+LangConfig.get(chatId, "statistic")));
        //row2
        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton(Emojis.COMMENT+LangConfig.get(chatId, "comment")));
        row3.add(new KeyboardButton(Emojis.LINKS+LangConfig.get(chatId, "links")));

        //row3
        KeyboardRow row4 = new KeyboardRow();
        row4.add(new KeyboardButton(Emojis.LANGUAGE+LangConfig.get(chatId, "language")));
        row4.add(new KeyboardButton(Emojis.ABOUT_US+LangConfig.get(chatId, "about.us")));
        row4.add(new KeyboardButton(Emojis.FEEDBACK+LangConfig.get(chatId, "subscribe.feedback")+Emojis.FEEDBACK));
        //row4
        KeyboardRow row5 = new KeyboardRow();
        row5.add(new KeyboardButton(Emojis.DONATE+LangConfig.get(chatId, "donate")));
        row5.add(new KeyboardButton(Emojis.HELP+LangConfig.get(chatId, "help")));
        row5.add(new KeyboardButton(Emojis.SETTINGS+LangConfig.get(chatId, "settings")));

        board.setKeyboard(List.of(row1, row2, row3, row4, row5));
        board.setResizeKeyboard(true);
        board.setSelective(true);
        return board;
    }

    public static ReplyKeyboardMarkup start(String chatId) {
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(LangConfig.get(chatId, "start")));
        board.setKeyboard(List.of(row));
        board.setResizeKeyboard(true);
        board.setSelective(true);
        return board;
    }

    public static ReplyKeyboard link(String chatId) {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(Emojis.CHANNEL+LangConfig.get(chatId, "channel")+Emojis.CHANNEL));
        row1.add(new KeyboardButton(Emojis.GROUP+LangConfig.get(chatId, "group")+Emojis.GROUP));
        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(Emojis.GO_BACK+LangConfig.get(chatId, "go.back")));
        board.setKeyboard(List.of(row1, row2));
        board.setResizeKeyboard(true);
        board.setSelective(true);
        return board;

    }

    public static ReplyKeyboardMarkup contactUs(String chatId) {
        //row1
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("Husan"));
        row1.add(new KeyboardButton("Axrullo"));
        //row2
        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("Aziza"));
        row2.add(new KeyboardButton("Uchqun"));
        //row3
        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton(Emojis.GROUP+LangConfig.get(chatId, "group")+Emojis.GROUP));
        row3.add(new KeyboardButton(Emojis.GO_BACK+LangConfig.get(chatId, "go.back")));
        board.setKeyboard(List.of(row1, row2, row3));
        board.setResizeKeyboard(true);
        board.setSelective(true);
        return board;
    }

    public static ReplyKeyboard report(String chatId) {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(Emojis.OK+LangConfig.get(chatId, "ok")));
        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(Emojis.CANCEL+LangConfig.get(chatId, "cancel")));
        board.setKeyboard(List.of(row1, row2));
        board.setResizeKeyboard(true);
        board.setSelective(true);
        return board;
    }

    public static ReplyKeyboardMarkup category(String chatId) {

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(Emojis.SCIENCE+LangConfig.get(chatId, "science")));
        row1.add(new KeyboardButton(Emojis.ADVENTURE+LangConfig.get(chatId, "adventure")));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(Emojis.HISTORY+LangConfig.get(chatId, "history")));
        row2.add(new KeyboardButton(Emojis.ROMANCE+LangConfig.get(chatId, "romance")));

        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton(Emojis.FANTASY+LangConfig.get(chatId, "fantasy")));
        row3.add(new KeyboardButton(Emojis.DETECTIVE+LangConfig.get(chatId, "detective")));

        KeyboardRow row4 = new KeyboardRow();
        row4.add(new KeyboardButton(Emojis.COMMENT+LangConfig.get(chatId, "comment")));
        row4.add(new KeyboardButton(Emojis.GO_BACK+LangConfig.get(chatId, "go.back")));

        board.setKeyboard(List.of(row1, row2, row3, row4));
        board.setResizeKeyboard(true);
        board.setSelective(true);
        return board;
    }


}
