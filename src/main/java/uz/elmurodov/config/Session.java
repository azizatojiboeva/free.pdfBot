package uz.elmurodov.config;

import lombok.Getter;
import lombok.Setter;
import uz.elmurodov.entity.User;


/**
 * @author Elmurodov Javohir, Wed 4:11 PM. 12/8/2021
 */

public class Session {
    private static Session session;

    @Getter
    @Setter
    private User user;

    public static Session getInstance() {
        if (session == null) {
            session = new Session();
            session.setUser(new User());
        }
        return session;
    }
}
