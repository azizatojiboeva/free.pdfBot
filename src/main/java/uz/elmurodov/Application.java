package uz.elmurodov;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * @author Elmurodov Javohir, Fri 5:11 PM. 12/17/2021
 */
public class Application {
    public static void main(String[] args) {
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(FreePdfBot.getInstance());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
