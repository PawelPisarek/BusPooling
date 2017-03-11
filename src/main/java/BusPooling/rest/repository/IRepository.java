package BusPooling.rest.repository;


import java.util.Collection;


public interface IRepository<T,E> {


    Collection<T> getAll();

    T addData(T data);

    E findById(String id);

    E save(E save);
}
