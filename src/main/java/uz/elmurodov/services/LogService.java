package uz.elmurodov.services;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.elmurodov.repository.LogRepository;

/**
 * @author Elmurodov Javohir, Sat 9:58 AM. 12/18/2021
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogService extends AbstractService<LogRepository> {
    private static final LogService instance = new LogService(LogRepository.getInstance());

    private LogService(LogRepository repository) {
        super(repository);
    }

    public void save(String data) {
        repository.save(data);
    }


    public static LogService getInstance() {
        return instance;
    }
}
