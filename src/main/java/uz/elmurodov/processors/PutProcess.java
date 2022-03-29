package uz.elmurodov.processors;

import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import uz.elmurodov.FreePdfBot;
import uz.elmurodov.buttons.InlineBoards;
import uz.elmurodov.buttons.MarkupBoard;
import uz.elmurodov.config.LangConfig;
import uz.elmurodov.config.PConfig;
import uz.elmurodov.config.State;
import uz.elmurodov.enums.state.AllState;
import uz.elmurodov.enums.state.BookState;
import uz.elmurodov.handlers.MessageHandler;
import uz.elmurodov.repository.LogRepository;

import java.io.File;
import java.util.Objects;

import static uz.elmurodov.FreePdfBot.users;
import static uz.elmurodov.config.State.setAllState;
import static uz.elmurodov.config.State.setBookState;

public class PutProcess {
    private static final PutProcess instance = new PutProcess();
    private static final FreePdfBot BOT = FreePdfBot.getInstance();
    private static final State instanceState= State.getInstance();

    public void Put(Message message, AllState state) {
        String chatID = message.getChatId().toString();
        if (AllState.FULL_NAME.equals(state) || Objects.isNull(state)) {
            SendMessage sendMessage = new SendMessage(chatID, LangConfig.get(chatID, "your.full.name.please"));
            sendMessage.setReplyMarkup(new ForceReplyKeyboard());
            BOT.executeMessage(sendMessage);
            setAllState(chatID, AllState.AGE);
        } else if (AllState.AGE.equals(state)) {
            users.get(message.getChatId()+"").setFullName(message.getText());
            SendMessage sendMessage = new SendMessage(chatID, LangConfig.get(chatID, "your.age.please"));
            sendMessage.setReplyMarkup(new ForceReplyKeyboard());
            BOT.executeMessage(sendMessage);
            setAllState(chatID, AllState.GENDER);
        } else if (AllState.GENDER.equals(state)) {
            String text = message.getText();
            if (StringUtils.isNumeric(text)) {
                users.get(message.getChatId()+"").setAge(Integer.parseInt(text));
                SendMessage sendMessage1 = new SendMessage(chatID, LangConfig.get(chatID, "gender"));
                sendMessage1.setReplyMarkup(InlineBoards.gender(chatID));
                BOT.executeMessage(sendMessage1);
                setAllState(chatID, AllState.PHONE_NUMBER);
            } else {
                SendMessage sendMessage1 = new SendMessage(chatID, LangConfig.get(chatID, "invalid.number.format") + LangConfig.get(chatID, "please.send.correct.number"));
                BOT.executeMessage(sendMessage1);
            }
        }else if(AllState.PHONE_NUMBER.equals(state)){
            String chatId= message.getChatId().toString();
            if (message.hasContact()) {
                users.get(
                        message.getContact().getUserId() + "").setPhoneNumber(message.getContact().getPhoneNumber());
                SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId, "successfully.authorized"));
                sendMessage.setReplyMarkup(MarkupBoard.mainMenu(chatId));

                BOT.executeMessage(sendMessage);
                setAllState(chatId, AllState.AUTHORIZED);
                sendMessage.setReplyMarkup(new ReplyKeyboardRemove());

                users.get(chatId).setRole("user");
                users.get(chatId).setUserName(message.getChat().getUserName());
                setAllState(message.getChatId().toString(),AllState.AUTHORIZED);
                LogRepository.getInstance().save(users.get(chatID), chatID, AllState.AUTHORIZED.toString());
            }
            else {
                SendMessage sendMessage1 = new SendMessage(chatId, "Invalid Number format\nPlease send correct number");
                BOT.executeMessage(sendMessage1);
            }
        }
    }

    public void sendMeBook(String chatId, Document document) {

        MessageHandler.bookStatus.put(chatId, BookState.ID);
        SendMessage sendMessage = new SendMessage(chatId, document.getFileId());
        BOT.executeMessage(sendMessage);
    }
    public void putBooks(String chatId) {

        forceReplyKeyboard(chatId, "Please send me any book ...");

    }

    public void forceReplyKeyboard(String chatID, String message) {
        SendMessage sendMessage = new SendMessage(chatID, message);
        sendMessage.setReplyMarkup(new ForceReplyKeyboard());
        BOT.executeMessage(sendMessage);
    }

    public void languageChoice(String chatID) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatID);
        sendPhoto.setPhoto(new InputFile(new File(PConfig.get("bot.logo"))));
        BOT.executeMessage(sendPhoto);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setText("Your language please");
        sendMessage.setReplyMarkup(InlineBoards.languageButtons());
        BOT.executeMessage(sendMessage);

    }

    public void initBook(Message message, BookState state) {
        String chatID = message.getChatId().toString();
        if (BookState.NAME.equals(state) || Objects.isNull(state)) {
            SendMessage sendMessage = new SendMessage(chatID, LangConfig.get(chatID, "your.full.name.please"));
            sendMessage.setReplyMarkup(new ForceReplyKeyboard());
            BOT.executeMessage(sendMessage);
            setBookState(chatID, BookState.AUTHOR);
        } else if (BookState.AUTHOR.equals(state)) {
            users.get(message.getChatId()+"").setFullName(message.getText());
            SendMessage sendMessage = new SendMessage(chatID, LangConfig.get(chatID, "your.age.please"));
            sendMessage.setReplyMarkup(new ForceReplyKeyboard());
            BOT.executeMessage(sendMessage);
            setBookState(chatID, BookState.DESCRIPTION);
        } else if (BookState.DESCRIPTION.equals(state)) {
            users.get(message.getChatId()+"").setFullName(message.getText());
            SendMessage sendMessage = new SendMessage(chatID, LangConfig.get(chatID, "your.age.please"));
            sendMessage.setReplyMarkup(new ForceReplyKeyboard());
            BOT.executeMessage(sendMessage);
            setBookState(chatID, BookState.CATEGORY);
        } else if (BookState.CATEGORY.equals(state)) {
            String text = message.getText();
            if (StringUtils.isNumeric(text)) {
                users.get(message.getChatId()+"").setAge(Integer.parseInt(text));
                SendMessage sendMessage1 = new SendMessage(chatID, LangConfig.get(chatID, "gender"));
                sendMessage1.setReplyMarkup(InlineBoards.category(chatID));
                BOT.executeMessage(sendMessage1);
                setBookState(chatID, BookState.CATEGORY);
            } else {
                SendMessage sendMessage1 = new SendMessage(chatID, LangConfig.get(chatID, "invalid.number.format") + LangConfig.get(chatID, "please.send.correct.number"));
                BOT.executeMessage(sendMessage1);
            }
        }else if(AllState.CATEGORY.equals(state)){
            String chatId= message.getChatId().toString();
            if (message.hasContact()) {
                users.get(
                        message.getContact().getUserId() + "").setPhoneNumber(message.getContact().getPhoneNumber());
                SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId, "successfully.authorized"));
                sendMessage.setReplyMarkup(MarkupBoard.mainMenu(chatId));

                BOT.executeMessage(sendMessage);
                setAllState(chatId, AllState.AUTHORIZED);
                sendMessage.setReplyMarkup(new ReplyKeyboardRemove());

                users.get(chatId).setRole("user");
                users.get(chatId).setUserName(message.getChat().getUserName());
                setAllState(message.getChatId().toString(),AllState.AUTHORIZED);
                LogRepository.getInstance().save(users.get(chatID), chatID, AllState.AUTHORIZED.toString());
            }

        }
    }


    public static PutProcess getInstance() {
        return instance;
    }
}
