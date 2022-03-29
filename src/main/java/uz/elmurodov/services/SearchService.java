package uz.elmurodov.services;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import uz.elmurodov.FreePdfBot;
import uz.elmurodov.buttons.utilsInlineBoard;
import uz.elmurodov.config.LangConfig;
import uz.elmurodov.entity.Book;
import uz.elmurodov.repository.bookRepository.BookRepository;

import java.util.List;


import static uz.elmurodov.FreePdfBot.SearchStatus;
import static uz.elmurodov.handlers.CallbackHandler.count;
import static uz.elmurodov.handlers.CallbackHandler.messages;
import static uz.elmurodov.handlers.MessageHandler.bookMap;

/**
 * @author Narzullayev Husan, вс 10:08. 02.01.2022
 */

public class SearchService {
private static final SearchService instance = new SearchService();
private static final FreePdfBot BOT = FreePdfBot.getInstance();

    public void search(String chatId, String message) {
        List<Book> books = BookRepository.get(message, 0);
        if(books.size() == 0){
            SendMessage sendMessage =new SendMessage(chatId, LangConfig.get(chatId,"book.not.found"));
            SearchStatus.put(chatId,"0");
            BOT.executeMessage(sendMessage);
            return;
        }

        count.put(chatId,0);
        bookMap.put(chatId,books);
        messages.put(chatId,message);
        utilsInlineBoard.next(books,chatId);
    }


    public static SearchService getInstance() {
        return instance;
    }
}
