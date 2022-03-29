package uz.elmurodov.services.authuser;


import org.telegram.telegrambots.meta.api.objects.Message;
import uz.elmurodov.repository.authuser.AuthUserRepository;
import uz.elmurodov.services.AbstractService;

/**
 * @author Narzullayev Husan, чт 23:32. 30.12.2021
 */
public class AuthUserService extends AbstractService<AuthUserRepository> {
    private static final AuthUserService instance = new AuthUserService(AuthUserRepository.getInstance());
    private static final AuthUserRepository authUserRepository = AuthUserRepository.getInstance();

    public void save(Message message) {
        String chatId = message.getChatId().toString();
        String userName = message.getFrom().getUserName();
        authUserRepository.save(chatId);
        authUserRepository.save("user_name", userName, chatId);
    }

    public AuthUserService(AuthUserRepository repository) {
        super(repository);
    }

    public static AuthUserService getInstance() {
        return instance;
    }
}
