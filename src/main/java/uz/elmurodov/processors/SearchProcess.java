package uz.elmurodov.processors;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import uz.elmurodov.FreePdfBot;
import uz.elmurodov.buttons.MarkupBoard;
import uz.elmurodov.config.LangConfig;
import uz.elmurodov.entity.Book;
import uz.elmurodov.enums.state.AllState;
import uz.elmurodov.enums.state.BookState;
import uz.elmurodov.services.OtherService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Narzullayev Husan, пт 10:23. 31.12.2021
 */
public class SearchProcess {
    private static final SearchProcess instance=new SearchProcess();
    private static final OtherService otherService = OtherService.getInstance();
    private static final FreePdfBot BOT = FreePdfBot.getInstance();
    public static Book book;
    public static final Map<String, BookState> bookStatus = new HashMap<>();

    public void search(String chatId, AllState state){

        if (state.equals(AllState.SEARCH)){
            bookStatus.put(chatId, BookState.ID);
            book = new Book();
            SendMessage sendMessage = new SendMessage(chatId,LangConfig.get(chatId,"search.report") );
            sendMessage.setReplyMarkup( MarkupBoard.category(chatId));
            BOT.executeMessage(sendMessage);
        }
    }


    public static SearchProcess getInstance() {
        return instance;
    }

}
