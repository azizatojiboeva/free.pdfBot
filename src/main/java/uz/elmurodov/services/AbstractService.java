package uz.elmurodov.services;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Elmurodov Javohir, Wed 11:21 AM. 12/15/2021
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractService<R> {
    protected R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }
}
