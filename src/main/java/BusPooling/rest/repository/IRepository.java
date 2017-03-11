package BusPooling.rest.repository;


import java.util.Collection;


public interface IRepository<T> {


    Collection<T> getAll();
    T addData(T data);

}
