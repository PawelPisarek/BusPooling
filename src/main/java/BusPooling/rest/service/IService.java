package BusPooling.rest.service;

/**
 * Created by pawe on 3/3/17.
 */
public interface IService<T> {
    void addFromHandle(T command);
}
