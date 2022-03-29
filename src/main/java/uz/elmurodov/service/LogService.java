package uz.elmurodov.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.elmurodov.repository.LogRepository;

/**
 * @author Elmurodov Javohir, Fri 5:43 PM. 12/17/2021
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogService {
    public static final LogRepository repository = LogRepository.getInstance();
    private static  LogService instance;

    public void create(String log) {
        repository.save(log);
    }


    public static LogService getInstance() {
        if (instance == null) {
            instance = new LogService();
        }
        return instance;
    }
}
