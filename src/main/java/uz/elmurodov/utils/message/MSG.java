package uz.elmurodov.utils.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

/**
 * @author Elmurodov Javohir, Sat 3:52 PM. 12/18/2021
 */
public class MSG {
    public static final SendMessage message = new SendMessage();
    public static final DeleteMessage delete = new DeleteMessage();
    public static final SendPhoto photo = new SendPhoto();
}
