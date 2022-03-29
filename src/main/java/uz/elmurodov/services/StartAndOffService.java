package uz.elmurodov.services;


import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;

import org.telegram.telegrambots.meta.api.objects.InputFile;


import uz.elmurodov.FreePdfBot;


import uz.elmurodov.config.LangConfig;
import uz.elmurodov.config.PConfig;


import uz.elmurodov.handlers.MessageHandler;

import java.io.File;

import static uz.elmurodov.config.GetAll.updates;

/**
 * @author Narzullayev Husan, вт 23:30. 28.12.2021
 */
public class StartAndOffService {
    private static final StartAndOffService instance = new StartAndOffService();
    private static final MessageHandler messageHandler = MessageHandler.getInstance();
    private static final FreePdfBot BOT = FreePdfBot.getInstance();
    private static final OtherService otherService = OtherService.getInstance();

    public void start( String chatId) {
        SendAnimation sendAnimation = new SendAnimation();
        sendAnimation.setChatId(chatId);
        sendAnimation.setAnimation(new InputFile(new File(PConfig.get("start.sticker"))));
        BOT.executeMessage(sendAnimation);
        otherService.sendMessage( chatId, " \uD83D\uDC4B Hi I am a bot."+
                "\n" +
                "I will find the books you need." +
                "\n\n"+
                "Use /off to pause your subscription.");
    }

    public void off(String chatId) {
        SendAnimation sendAnimation = new SendAnimation();
        sendAnimation.setChatId(chatId);
        sendAnimation.setAnimation(new InputFile(new File(PConfig.get("off.sticker"))));
        BOT.executeMessage(sendAnimation);
    }

    public void on(String chatId) {
        SendAnimation sendAnimation = new SendAnimation();
        sendAnimation.setChatId(chatId);
        sendAnimation.setAnimation(new InputFile(new File(PConfig.get("off.sticker"))));
        BOT.executeMessage(sendAnimation);

    }

    public static StartAndOffService getInstance() {
        return instance;
    }
}
