package uz.elmurodov;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.elmurodov.config.CommentGet;
import uz.elmurodov.config.GetAll;
import uz.elmurodov.config.PConfig;
import uz.elmurodov.entity.Comment;
import uz.elmurodov.entity.User;
import uz.elmurodov.handlers.UpdateHandler;
import uz.elmurodov.service.LogService;
import uz.elmurodov.services.authuser.AuthUserService;
import uz.elmurodov.utils.Util;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * @author Elmurodov Javohir, Fri 5:38 PM. 12/17/2021
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FreePdfBot extends TelegramLongPollingBot {
    public static final Map<String, Comment> comments = CommentGet.getInstance().getAll();
    public static final Map<String, User> users = GetAll.getInstance().getAll();
    public static final Map<String,String>SearchStatus=new HashMap<>();
    private static final FreePdfBot instance = new FreePdfBot();
    public static final UpdateHandler updateHandler = UpdateHandler.getInstance();
    public static final LogService logService=LogService.getInstance();
    public static final AuthUserService authUserService= AuthUserService.getInstance();




    @Override
    public void onUpdateReceived(Update update) {
        logService.create(Util.asString(update));
        updateHandler.handle(update);
    }

    @Override
    public String getBotToken() {
        return PConfig.get("bot.token");
    }

    @Override
    public String getBotUsername() {
        return PConfig.get("bot.username");
    }

    public void executeMessage(BotApiMethod<?> msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void executeMessage(SendMessage msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void executeMessage(SendDocument msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void executeMessage(SendPhoto msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void executeMessage(SendAnimation msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static FreePdfBot getInstance() {
        return instance;
    }
}
