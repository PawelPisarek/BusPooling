package BusPooling.rest.service;

import java.util.Collection;

/**
 * Created by pawe on 3/3/17.
 */
public interface IService<T, U, O> {
    Collection<O> getAll();

    void addFromHandle(T command);

    void update(U command);
}
