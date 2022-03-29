package uz.elmurodov.handlers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import uz.elmurodov.FreePdfBot;
import uz.elmurodov.buttons.InlineBoards;
import uz.elmurodov.buttons.MarkupBoard;
import uz.elmurodov.buttons.utilsInlineBoard;
import uz.elmurodov.config.LangConfig;
import uz.elmurodov.config.PConfig;
import uz.elmurodov.emojis.Emojis;
import uz.elmurodov.entity.Book;
import uz.elmurodov.entity.Comment;
import uz.elmurodov.enums.Language;
import uz.elmurodov.enums.state.AllState;
import uz.elmurodov.enums.state.BookState;
import uz.elmurodov.processors.PutProcess;
import uz.elmurodov.processors.SearchProcess;
import uz.elmurodov.repository.authuser.AuthUserRepository;
import uz.elmurodov.repository.bookRepository.BookRepository;
import uz.elmurodov.service.LogService;
import uz.elmurodov.services.*;
import uz.elmurodov.services.search.LanguageService;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uz.elmurodov.config.State.getAllState;
import static uz.elmurodov.handlers.CallbackHandler.count;
import static uz.elmurodov.handlers.CallbackHandler.messages;


/**
 * @author Elmurodov Javohir, Fri 6:44 PM. 12/17/2021
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageHandler {

    private static final MessageHandler instance = new MessageHandler();

    private static final LogService service = LogService.getInstance();
    private static final AuthUserRepository authUserRepository = AuthUserRepository.getInstance();
    private static final FreePdfBot BOT = FreePdfBot.getInstance();
    private static final OtherService otherService = OtherService.getInstance();
    private static final ContactService contactService = ContactService.getInstance();
    private static final StartAndOffService service1 = StartAndOffService.getInstance();
    private static final LanguageService languageService = LanguageService.getInstance();
    private static final SearchService searchService = SearchService.getInstance();
    private static final SearchProcess searchProcess = SearchProcess.getInstance();
    private static final PutProcess putProcess = new PutProcess();
    private static final LinkService linkService = LinkService.getInstance();
    private static final SettingService settingService = SettingService.getInstance();
    public static Language language;
    public static Book book;
    public static Comment comment;
    public static final Map<String, BookState> bookStatus = new HashMap<>();
    public static final Map<String, List<Book>> bookMap = new HashMap<>();
    public static final Map<String, List<Comment>> commentMap = new HashMap<>();

    public void handle(Message message) {
        service.create(message.getText());
        String chatId = message.getChatId().toString();
        AllState state = getAllState(chatId);


        if (message.hasContact()) {
            putProcess.Put(message, state);
        }


        if (AllState.AUTHORIZED.equals(state) && ("/start".equals(message.getText()) ||
                message.getText().equals(LangConfig.get(chatId, "start")))) {
            SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId, "welcome.to.our.library"));
            sendMessage.setReplyMarkup(MarkupBoard.mainMenu(chatId));
            BOT.executeMessage(sendMessage);
        } else if (("/start".equals(message.getText()) ||
                LangConfig.get(chatId, "start").equals(message.getText())) && !AllState.AUTHORIZED.equals(state)) {
            service1.start(chatId);
            putProcess.languageChoice(chatId);
            SendMessage sendMessage = new SendMessage(chatId, "Welcome to our library");
            BOT.executeMessage(sendMessage);
        } else if (bookStatus.get(chatId) != null) {

            if (bookStatus.get(chatId).equals(BookState.DESCRIPTION)) {
                book.setDescription(message.getText());
                SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId, "book.author"));
                bookStatus.put(chatId, BookState.AUTHOR);
                FreePdfBot.getInstance().executeMessage(sendMessage);
            } else if (bookStatus.get(chatId).equals(BookState.AUTHOR)) {
                book.setAuthor(message.getText());
                SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId, "category"));
                sendMessage.setReplyMarkup(InlineBoards.category(chatId));
                bookStatus.put(chatId, BookState.CATEGORY);
                BOT.executeMessage(sendMessage);

            }
        } else if (bookStatus.get(chatId) != null && message.hasDocument() && bookStatus.get(chatId).equals(BookState.ID)) {
            book.setId(message.getDocument().getFileId());
            book.setBookName(message.getDocument().getFileName());
            SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId, "book.desc"));
            bookStatus.put(chatId, BookState.DESCRIPTION);
            BOT.executeMessage(sendMessage);
        } else if ("/off".equals(message.getText())) {
            service1.off(chatId);
            SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId, "subscription") +
                    "\n\n" +
                    LangConfig.get(chatId, "again"));
            sendMessage.setReplyMarkup(MarkupBoard.start(chatId));
            BOT.executeMessage(sendMessage);
        } else if ("/on".equals(message.getText())) {
            service1.on(chatId);
            String message1 = LangConfig.get(chatId, "on") +
                    "\n" +
                    LangConfig.get(chatId, "off") + "\n";
            SendMessage sendMessage = new SendMessage(chatId, message1);
            sendMessage.setReplyMarkup(MarkupBoard.mainMenu(chatId));
            BOT.executeMessage(sendMessage);
        } else if (message.getText().equals(Emojis.SEARCH + LangConfig.get(chatId, "search")) || "/search".equals(message.getText())) {
            searchProcess.search(chatId, AllState.SEARCH);
            searchService.search(chatId, message.getText());
        } else if ((authUserRepository.isHaveUser(chatId) && message.getText().equals(Emojis.ADD + LangConfig.get(chatId, "put"))) ||
                (authUserRepository.isHaveUser(chatId) && message.getText().equals("/put"))) {
            putProcess.putBooks(message.getChatId().toString());
        } else if (!authUserRepository.isHaveUser(chatId) && message.getText().equals(Emojis.ADD + LangConfig.get(chatId, "put")) ||
                (!authUserRepository.isHaveUser(chatId) && message.getText().equals("/put"))) {
            putProcess.Put(message, state);
        } else if ("/contact_us".equals(message.getText()) ||
                message.getText().equals(Emojis.CONTACT_US + LangConfig.get(chatId, "contact.us"))) {
            contactService.contactUs(chatId);
        } else if ("/language".equals(message.getText()) ||
                message.getText().equals(Emojis.LANGUAGE + LangConfig.get(chatId, "language"))) {
            languageService.lang(chatId);
        } else if ("/links".equals(message.getText()) ||
                message.getText().equals(Emojis.LINKS + LangConfig.get(chatId, "links"))) {
            linkService.link(chatId);
        } else if ("/comments".equals(message.getText()) ||
                message.getText().equals(Emojis.FEEDBACK + LangConfig.get(chatId, "subscribe.feedback") + Emojis.FEEDBACK)) {
            otherService.getComments(chatId);
        } else if (message.getText().equals(Emojis.COMMENT + LangConfig.get(chatId, "comment")) || message.getText().equals("/report")) {
            otherService.report(chatId, AllState.COMMENT);
        } else if (state == AllState.COMMENT) {
            otherService.getComment(chatId, message);
        } else if (message.getText().equals("/users")) {
            otherService.sendMessage(message, chatId, LangConfig.get(chatId, "users"));
            // userslar soni chiqadi
        } else if (message.getText().equals(Emojis.ABOUT_US + LangConfig.get(chatId, "about.us"))) {
            otherService.aboutUs(chatId);
        } else if (message.getText().equals("/help") || message.getText().equals(Emojis.HELP + LangConfig.get(chatId, "help"))) {
            otherService.help(chatId);
        } else if (message.getText().equals("/settings") ||
                message.getText().equals(Emojis.SETTINGS + LangConfig.get(chatId, "settings"))) {
            // language iwlayapti
            // age bn fullname logikasi qoldi u faqat put qiladiganlarga kurinadigan qilamiz
            settingService.setting(chatId);

        } else if (message.getText().equals(Emojis.DONATE + LangConfig.get(chatId, "donate"))) {
            otherService.donate(chatId);
        } else if (message.getText().equals(Emojis.CHANNEL + LangConfig.get(chatId, "channel") + Emojis.CHANNEL)) {
            otherService.sendMessage(message, chatId, "https://t.me/PDFBOOKSYOUNEED");
        } else if (message.getText().equals(Emojis.GROUP + LangConfig.get(chatId, "group") + Emojis.GROUP)) {
            otherService.sendMessage(message, chatId, "https://t.me/+ertcIJalI4g5OWI6");
        } else if (message.getText().equals(Emojis.GO_BACK + LangConfig.get(chatId, "go.back"))) {
            otherService.menuExecte(chatId);
        } else if (message.getText().equals("Husan")) {
            String name = "husan";
            sendPhoto(chatId, name);
            otherService.sendMessage(message, chatId, "<a href=\"https://t.me/Narzullayev_Husan\"> Husan</a>");
        } else if (message.getText().equals("Axrullo")) {
            String name = "axrullo";
            sendPhoto(chatId, name);
            otherService.sendMessage(message, chatId, "<a href=\"https://t.me/akhrullo\">Axrullo</a>");
        } else if (message.getText().equals("Aziza")) {
            String name = "aziza";
            sendPhoto(chatId, name);
            otherService.sendMessage(message, chatId, "<a href=\"https://t.me/AzizaTojiboeva\">Aziza</a>");
        } else if (message.getText().equals("Uchqun")) {
            String name = "uchqun";
            sendPhoto(chatId, name);
            otherService.sendMessage(message, chatId, "<a href=\"https://t.me/Uchqun99bek26\">Uchqun</a>");
        } else if (bookStatus.get(chatId) != null) {

            if (bookStatus.get(chatId).equals(BookState.DESCRIPTION)) {
                book.setDescription(message.getText());
                SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId, "book.author"));
                bookStatus.put(chatId, BookState.AUTHOR);
                FreePdfBot.getInstance().executeMessage(sendMessage);
            } else if (bookStatus.get(chatId).equals(BookState.AUTHOR)) {
                book.setAuthor(message.getText());
                SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId, "category"));
                sendMessage.setReplyMarkup(InlineBoards.category(chatId));
                bookStatus.put(chatId, BookState.CATEGORY);
                BOT.executeMessage(sendMessage);

            }
        } else if (bookStatus.get(chatId) != null && message.hasDocument() && bookStatus.get(chatId).equals(BookState.ID)) {
            book.setId(message.getDocument().getFileId());
            book.setBookName(message.getDocument().getFileName());
            SendMessage sendMessage = new SendMessage(chatId, LangConfig.get(chatId, "book.desc"));
            bookStatus.put(chatId, BookState.DESCRIPTION);
            BOT.executeMessage(sendMessage);
        } else if (message.getText().equals(Emojis.MY_BOOKS + LangConfig.get(chatId, "my.books"))) {
            count.put(chatId, 0);
            List<Book> books = BookRepository.getMY(chatId, 0);
            messages.put(chatId, chatId);
            utilsInlineBoard.next(books, chatId);

        } else if (message.getText().equals(LangConfig.get(chatId, "science"))) {


        } else if (message.getText().equals(LangConfig.get(chatId, "adventure"))) {


        } else if (message.getText().equals(LangConfig.get(chatId, "history"))) {


        } else if (message.getText().equals(LangConfig.get(chatId, "romance"))) {


        } else if (message.getText().equals(LangConfig.get(chatId, "fantasy"))) {


        } else if (message.getText().equals(LangConfig.get(chatId, "detective"))) {

        } else {
//            putProcess.Put(message,state);
        }
    }

    private void sendPhoto(String chatId, String name) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile(new File(PConfig.get(name))));
        BOT.executeMessage(sendPhoto);
    }

    private boolean isUserAuthorized(AllState state) {
        return AllState.AUTHORIZED.equals(state);
    }

    public static void delete(Message message, String chatId) {
        DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
        BOT.executeMessage(deleteMessage);
    }

    public void removeKeyboard(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true, false));
        BOT.executeMessage(sendMessage);
    }


    public static MessageHandler getInstance() {
        return instance;
    }


}
