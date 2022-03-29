package uz.elmurodov.services;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import uz.elmurodov.FreePdfBot;
import uz.elmurodov.buttons.MarkupBoard;
import uz.elmurodov.config.LangConfig;
import uz.elmurodov.config.PConfig;
import uz.elmurodov.handlers.MessageHandler;

import java.io.File;

/**
 * @author Narzullayev Husan, ср 10:19. 29.12.2021
 */
public class LinkService {
    private static final LinkService instance=new LinkService();
    private static final FreePdfBot BOT = FreePdfBot.getInstance();
    private static final MessageHandler messageHandler = MessageHandler.getInstance();


    public void link(String chatId){
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile(new File(PConfig.get("link"))));
        BOT.executeMessage(sendPhoto);
        SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId,"channel")+
                "\n"+
                "https://t.me/PDFBOOKSYOUNEED\n"+
                LangConfig.get(chatId,"group")+
                "\n"+
                "https://t.me/tuit_mehrli_qollar\n");
        sendMessage.setReplyMarkup(MarkupBoard.link(chatId));
        BOT.executeMessage(sendMessage);
    }

    public static LinkService getInstance() {
        return instance;
    }
}
