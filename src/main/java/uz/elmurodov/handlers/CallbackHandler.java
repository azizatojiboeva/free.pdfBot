package uz.elmurodov.handlers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import uz.elmurodov.FreePdfBot;
import uz.elmurodov.buttons.InlineBoards;
import uz.elmurodov.buttons.MarkupBoard;
import uz.elmurodov.config.LangConfig;


import uz.elmurodov.emojis.Emojis;


import uz.elmurodov.entity.Book;
import uz.elmurodov.entity.Comment;
import uz.elmurodov.enums.state.AllState;
import uz.elmurodov.enums.Language;

import uz.elmurodov.repository.bookRepository.BookRepository;
import uz.elmurodov.repository.commentRepository.CommentRepository;
import uz.elmurodov.services.ContactService;
import uz.elmurodov.services.OtherService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uz.elmurodov.FreePdfBot.users;

import static uz.elmurodov.buttons.utilsInlineBoard.next;
import static uz.elmurodov.buttons.utilsInlineBoard.nextForComment;
import static uz.elmurodov.config.GetAll.updates;
import static uz.elmurodov.config.State.*;
import static uz.elmurodov.handlers.MessageHandler.*;


/**
 * @author Elmurodov Javohir, Fri 6:48 PM. 12/17/2021
 */


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CallbackHandler {
    private static CallbackHandler instance = new CallbackHandler();
    private static final FreePdfBot BOT = FreePdfBot.getInstance();
    public static Language language;
    private static final OtherService otherService = OtherService.getInstance();
    private static final ContactService contactService = ContactService.getInstance();
    public static Map<String, String> messages = new HashMap<>();
    public static Map<String, Integer> count = new HashMap<>();

    public void handle(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String data = callbackQuery.getData();
        String chatID = message.getChatId().toString();
        switch (data) {
            case "uz", "ru", "en" -> {
                System.out.println(data);
                DeleteMessage deleteMessage = new DeleteMessage(chatID, message.getMessageId());
                BOT.executeMessage(deleteMessage);
                users.get(chatID).setLanguage(data);
                SendMessage sendMessage = new SendMessage(chatID, LangConfig.get(chatID, "change.language"));
                BOT.executeMessage(sendMessage);
                otherService.menuExecte(chatID);
                //   isUserUndefined.menu(message);
                updates(chatID, data);
            }
            case "male", "female" -> {
                DeleteMessage deleteMessage = new DeleteMessage(chatID, message.getMessageId());
                BOT.executeMessage(deleteMessage);
                SendMessage sendMessage = new SendMessage(chatID, Emojis.PHONE + LangConfig.get(chatID, "share.phone.number"));
                sendMessage.setReplyMarkup(MarkupBoard.sharePhoneNumber(chatID));
                BOT.executeMessage(sendMessage);
                users.get(chatID).setGender(callbackQuery.getData());
                updates(chatID, data);
                setAllState(chatID, AllState.PHONE_NUMBER);
            }
            case "donate" -> {
                otherService.sendMessage(chatID, LangConfig.get(chatID, "pay"));
                contactService.contactUs(chatID);
            }
            case "row1", "row2", "row3", "row4", "row5", "row6" -> {
                DeleteMessage deleteMessage = new DeleteMessage(chatID, message.getMessageId());
                BOT.executeMessage(deleteMessage);
                book.setCategory(data);
                SendMessage sendMessage = new SendMessage(chatID, LangConfig.get(chatID, "book.add"));
                BOT.executeMessage(sendMessage);
                BookRepository.save(book, chatID);
            }
            case "next" -> {
                DeleteMessage deleteMessage = new DeleteMessage(chatID, callbackQuery.getMessage().getMessageId());
                FreePdfBot.getInstance().executeMessage(deleteMessage);
                List<Book> book = BookRepository.get(messages.get(chatID), count.get(chatID) + 5);
                next(book, chatID);
                bookMap.put(chatID, book);
                count.put(chatID, count.get(chatID) + 5);
            }
            case "nextForComment" -> {
                DeleteMessage deleteMessage = new DeleteMessage(chatID, callbackQuery.getMessage().getMessageId());
                FreePdfBot.getInstance().executeMessage(deleteMessage);
                List<Comment> comments = CommentRepository.get((chatID), count.get(chatID) + 5);
                commentMap.put(chatID, comments);
//                System.out.println(commentMap.get(chatID).get(1).getFullName());
                count.put(chatID, count.get(chatID) + 5);
                nextForComment(comments, chatID);

            }
            case "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" -> {
                int index = Integer.parseInt(data);
                if (index==1222) {
                    InputFile inputFile = new InputFile(bookMap.get(chatID).get(index).getId());
                    SendDocument sendDocument = new SendDocument(chatID, inputFile);
                    FreePdfBot.getInstance().executeMessage(sendDocument);
                } else {
                    StringBuilder msg = new StringBuilder();
                    msg = msg.append("<b> \uD83D\uDC64 ").append(commentMap.get(chatID).get(index).getFullName()).append(" :  </b>\n\n\t\t <i> - ").append(commentMap.get(chatID).get(index).getComment()).append("</i>");
                    SendMessage sendMessage = new SendMessage(chatID, msg.toString());
                    sendMessage.enableHtml(true);
                    FreePdfBot.getInstance().executeMessage(sendMessage);
                }

            }
            case "button1" -> {
                ///fullname
                SendMessage sendMessage = new SendMessage(chatID, LangConfig.get(chatID, "your.full.name.please"));
                sendMessage.setReplyMarkup(new ForceReplyKeyboard());
                BOT.executeMessage(sendMessage);
                users.get(chatID).setFullName(message.getText());
                updates(chatID, data);
                otherService.menuExecte(chatID);
            }
            case "button2" -> {
                ///age
                SendMessage sendMessage = new SendMessage(chatID, LangConfig.get(chatID, "your.age.please"));
                sendMessage.setReplyMarkup(new ForceReplyKeyboard());
                BOT.executeMessage(sendMessage);
                //users.get(chatID).setAge(Integer.parseInt(message.getText()));
                updates(chatID, data);
                otherService.menuExecte(chatID);
            }
            case "button3" -> {
                ///language
                SendMessage sendMessage = new SendMessage(chatID, LangConfig.get(chatID, "your.language.please"));
                sendMessage.setReplyMarkup(InlineBoards.languageButtons());
                BOT.executeMessage(sendMessage);
//                users.get(chatID).setLanguage(data);
//                otherService.menuExecte(chatID);
//                updates(chatID, data);

            }

        }
    }

    public static CallbackHandler getInstance() {
        return instance;
    }
}
