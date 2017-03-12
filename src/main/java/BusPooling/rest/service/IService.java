package BusPooling.rest.service;

/**
 * Created by pawe on 3/3/17.
 */
public interface IService<T,U> {
    void addFromHandle(T command);
    void update(U command);
}
