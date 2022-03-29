package uz.elmurodov.services;

import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import uz.elmurodov.FreePdfBot;
import uz.elmurodov.buttons.InlineBoards;
import uz.elmurodov.buttons.MarkupBoard;
import uz.elmurodov.buttons.utilsInlineBoard;
import uz.elmurodov.config.LangConfig;
import uz.elmurodov.config.PConfig;
import uz.elmurodov.emojis.Emojis;
import uz.elmurodov.entity.Comment;
import uz.elmurodov.enums.state.AllState;
import uz.elmurodov.handlers.MessageHandler;

import  uz.elmurodov.config.State;
import uz.elmurodov.repository.commentRepository.CommentRepository;
import static uz.elmurodov.handlers.CallbackHandler.count;
import static uz.elmurodov.handlers.CallbackHandler.messages;
import static uz.elmurodov.handlers.MessageHandler.commentMap;

import java.io.File;
import java.util.List;

/**
 * @author Narzullayev Husan, ср 0:25. 29.12.2021
 */
public class OtherService {
    private static final OtherService instance=new OtherService();
    private static final MessageHandler messageHandler = MessageHandler.getInstance();
    private static final FreePdfBot BOT = FreePdfBot.getInstance();
    private static final CommentRepository commentRepository = CommentRepository.getInstance();


    public  void donate(String chatId){

       SendMessage sendMessage = new SendMessage(chatId,"Donation"+
               "\n"+
               "for @MavericksLibrarybot\n"+
               "1,00 \uD83D\uDCB6 INVOICE");
       sendMessage.setReplyMarkup(InlineBoards.donate(chatId));
       BOT.executeMessage(sendMessage);

   }
   public  void help(String chatId){
      sendMessage( chatId, LangConfig.get(chatId, "help.commands") + "\n" +
               LangConfig.get(chatId, "help.second") + "\n" +
               LangConfig.get(chatId, "help.start") + "\n" +
               LangConfig.get(chatId, "help.search") + "\n" +
               LangConfig.get(chatId, "help.put") + "\n" +
               LangConfig.get(chatId, "help.users") + "\n" +
               LangConfig.get(chatId, "help.comments") + "\n" +
               LangConfig.get(chatId, "help.links") + "\n" +
               LangConfig.get(chatId, "help.language") + "\n" +
               LangConfig.get(chatId, "help.contact.us") + "\n" +
               LangConfig.get(chatId, "help.report") + "\n" +
               LangConfig.get(chatId, "help.settings") + "\n" +
               LangConfig.get(chatId, "helps") + "\n" +
               LangConfig.get(chatId, "help.off") + "\n" +
               LangConfig.get(chatId, "help.on") + "\n" +
               LangConfig.get(chatId, "help.contact") +
              " <a href=\"https://t.me/+ertcIJalI4g5OWI6\"> Mavericks </a>");

   }
    public void report(String chatId, AllState state) {
        SendAnimation sendAnimation = new SendAnimation();
        sendAnimation.setChatId(chatId);
        sendAnimation.setAnimation(new InputFile(new File(PConfig.get("report"))));
        BOT.executeMessage(sendAnimation);

        SendMessage sendMessage = new SendMessage(chatId, Emojis.REPORT_COMMENT+LangConfig.get(chatId, "report.comment"));
        sendMessage.setReplyMarkup(new ForceReplyKeyboard());
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true, false));
        State.setAllState(chatId, state);
        BOT.executeMessage(sendMessage);
    }
    public void getComment(String chatId, Message message) {
        State.setAllState(chatId, AllState.DELETE_ALL);
        Comment comment = new Comment();
        comment.setComment(message.getText());
        comment.setFullName(message.getFrom().getFirstName() + " " + (message.getChat().getLastName() == null?"":message.getChat().getLastName()));
        comment.setUserName(message.getFrom().getUserName());
        comment.setChatId(message.getFrom().getId().toString());
        commentRepository.save(comment);

        SendMessage smg = new SendMessage(chatId, LangConfig.get(chatId, "thanks.attention"));
        smg.setReplyMarkup(MarkupBoard.mainMenu(chatId));
        BOT.executeMessage(smg);
    }

    public void getComments(String chatId) {
        count.put(chatId, 0);
        List<Comment> comments = commentRepository.get(chatId, 0);
        commentMap.put(chatId, comments);


        utilsInlineBoard.nextForComment(comments, chatId);

    }

    public void sendMessage(Message message, String chatId, String message1) {
        SendMessage sendMessage = new SendMessage(chatId, message1);
        sendMessage.enableHtml(true);
        sendMessage.setReplyMarkup(message.getReplyMarkup());
        BOT.executeMessage(sendMessage);
    }
    public void sendMessage( String chatId, String message1) {
        SendMessage sendMessage = new SendMessage(chatId, message1);
        sendMessage.enableHtml(true);
        BOT.executeMessage(sendMessage);
    }

    public void menuExecte(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, Emojis.right + LangConfig.get(chatId, "main.menu") + Emojis.left);
        sendMessage.setReplyMarkup(MarkupBoard.mainMenu(chatId));
        BOT.executeMessage(sendMessage);
    }

    public void aboutUs(String chatId) {
        sendMessage(chatId,LangConfig.get(chatId,"about.1")+ "\n\n"+
                LangConfig.get(chatId, "about.2")+ "\n" +
                LangConfig.get(chatId, "about.7")+ " , " +
                LangConfig.get(chatId, "about.8")+ " , " +
                LangConfig.get(chatId, "about.9")+ " , " +
                LangConfig.get(chatId, "about.10")+ " , " +
                LangConfig.get(chatId, "about.11")+ "\n\n" +
                LangConfig.get(chatId, "about.3")+ "\n\n" +
                LangConfig.get(chatId, "about.4")+ "\n" +
                LangConfig.get(chatId, "about.5")+ "\n\n\n\n" +
                LangConfig.get(chatId, "about.6")+ "\n" );
    }



    public static OtherService getInstance() {
        return instance;
    }



}
