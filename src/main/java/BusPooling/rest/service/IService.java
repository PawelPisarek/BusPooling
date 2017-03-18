package BusPooling.rest.service;

import BusPooling.rest.aplication.command.Closure.IInformUsers;

import java.util.Collection;

/**
 * Created by pawe on 3/3/17.
 */
public interface IService<T, U, O> {
    Collection<O> getAll();

    void addFromHandle(T command);
    void addFromHandle(T command, IInformUsers informUsers);

    void update(U command);
}
