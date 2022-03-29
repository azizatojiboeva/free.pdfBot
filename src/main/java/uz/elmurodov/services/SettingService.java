package uz.elmurodov.services;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import uz.elmurodov.FreePdfBot;
import uz.elmurodov.buttons.InlineBoards;
import uz.elmurodov.config.LangConfig;

import static uz.elmurodov.FreePdfBot.users;

/**
 * @author Narzullayev Husan, вс 10:26. 02.01.2022
 */
public class SettingService {
    private static final SettingService instance=new SettingService();
    private static final OtherService otherService = OtherService.getInstance();
    private static final FreePdfBot BOT = FreePdfBot.getInstance();

    public void setting(String  chatId){

        SendMessage sendMessage = new SendMessage(chatId,LangConfig.get(chatId,"settings1")+
                "\n\n"+
                LangConfig.get(chatId,"settings2")+users.get(chatId).getFullName()+
                "\n\n"+
                LangConfig.get(chatId,"settings3")+users.get(chatId).getAge()+
                "\n\n"+
                LangConfig.get(chatId,"settings4")+users.get(chatId).getLanguage()+
                "\n\n"+
                LangConfig.get(chatId,"settings5")+
                "\n");
        sendMessage.setReplyMarkup(InlineBoards.settings(chatId));
        BOT.executeMessage(sendMessage);
    }
    public static SettingService getInstance() {
        return instance;
    }
}
