package uz.elmurodov.handlers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.elmurodov.entity.User;

import static uz.elmurodov.FreePdfBot.users;

/**
 * @author Elmurodov Javohir, Fri 6:33 PM. 12/17/2021
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateHandler {
    private static final UpdateHandler instance = new UpdateHandler();
    private final MessageHandler messageHandler = MessageHandler.getInstance();
    private final CallbackHandler callbackHandler = CallbackHandler.getInstance();

    public void handle(Update update) {
        if (update.hasMessage()){
            if(users.get(update.getMessage().getChatId()+"")==null){
                users.put(update.getMessage().getChatId()+"",new User());
            }
            messageHandler.handle(update.getMessage());
        }
        else if (update.hasCallbackQuery()) {
            if(users.get(update.getCallbackQuery().getMessage().getChatId()+"")==null){
                users.put(update.getCallbackQuery().getMessage().getChatId()+"",new User());
            }
            callbackHandler.handle(update.getCallbackQuery());
        }
        else System.out.println("Not Found");
    }

    public static UpdateHandler getInstance() {
        return instance;
    }
}
