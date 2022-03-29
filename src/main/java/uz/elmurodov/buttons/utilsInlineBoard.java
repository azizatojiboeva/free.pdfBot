package uz.elmurodov.buttons;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.elmurodov.FreePdfBot;
import uz.elmurodov.entity.Auditable;
import uz.elmurodov.entity.Book;
import uz.elmurodov.entity.Comment;

import java.util.ArrayList;
import java.util.List;

public class utilsInlineBoard {
    private static final InlineKeyboardMarkup inline = new InlineKeyboardMarkup();

    public static void next(List<Book> listEntity, String chatId) {
        String message = takeMessage(listEntity);
        List<List<InlineKeyboardButton>> lists = createButton(listEntity);
        if (listEntity.size() == 5) {
            List<InlineKeyboardButton> buttons1 = new ArrayList<>();
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton("next");
            inlineKeyboardButton.setCallbackData("next");
            buttons1.add(inlineKeyboardButton);
            lists.add(buttons1);
        }
        inline.setKeyboard(lists);
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.setReplyMarkup(inline);
        FreePdfBot.getInstance().executeMessage(sendMessage);
    }


    private static String  takeMessage(List<Book> listEntity) {
        StringBuilder returns = new StringBuilder();
        for (int i = 1; i <= listEntity.size(); i++) {
            returns.append(i).append(". ").append(listEntity.get(i - 1).getBookName()).append("  author:  ").append(listEntity.get(i - 1).getAuthor()).append("\n");
        }
        return returns.toString();
    }

    private static <E extends Auditable> List<List<InlineKeyboardButton>> createButton(List<E> listEntity) {
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        List<InlineKeyboardButton> buttons2 = new ArrayList<>();
        for (int i = 0; i < listEntity.size(); i++) {
            if (i >=5) {
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton((i + 1) + "");
                inlineKeyboardButton.setCallbackData(i+"");
                buttons2.add(inlineKeyboardButton);
            } else {
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton((i + 1) + "");
                inlineKeyboardButton.setCallbackData(i+"");
                buttons1.add(inlineKeyboardButton);
            }
        }

        List<List<InlineKeyboardButton>> lists = new ArrayList<>();
        lists.add(buttons1);
        lists.add(buttons2);
        return lists;

    }

    public static void nextForComment(List<Comment> listEntity, String chatId) {
        String message = getComment(listEntity);
        List<List<InlineKeyboardButton>> lists = createButton(listEntity);
        if (listEntity.size() == 5) {
            List<InlineKeyboardButton> buttons1 = new ArrayList<>();
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton("next");
            inlineKeyboardButton.setCallbackData("nextForComment");
            buttons1.add(inlineKeyboardButton);
            lists.add(buttons1);
        }
        inline.setKeyboard(lists);
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.setReplyMarkup(inline);
        FreePdfBot.getInstance().executeMessage(sendMessage);
    }

    private static String getComment(List<Comment> listEntity) {
        StringBuilder returns = new StringBuilder();
        for (int i = 1; i <= listEntity.size(); i++) {
            returns.append(i).append(". ").append(listEntity.get(i - 1).getFullName()).append("  :  ").append(listEntity.get(i - 1).getComment()).append("\n");
        }
        return returns.toString();
    }
}
