package uz.elmurodov.utils.message;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

/**
 * @author Elmurodov Javohir, Sat 3:53 PM. 12/18/2021
 */
public class MessageFactory {
    public static PartialBotApiMethod<?> build(MessageType type, String chatId, String messageIdOrText) {
        if (MessageType.MESSAGE.equals(type)) {
            SendMessage message = MSG.message;
            message.setChatId(chatId);
            message.setText(messageIdOrText);
            return message;
        }
        if (MessageType.DELETE.equals(type)){
            DeleteMessage message = MSG.delete;
            message.setChatId(chatId);
            message.setMessageId(Integer.parseInt(messageIdOrText));
            return message;
        }
        SendPhoto message = MSG.photo;
        message.setChatId(chatId);
        message.setCaption(messageIdOrText);
        return message;
    }
}
