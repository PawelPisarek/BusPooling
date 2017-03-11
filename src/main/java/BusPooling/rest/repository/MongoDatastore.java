package BusPooling.rest.repository;

import org.mongodb.morphia.Datastore;

/**
 * Created by pawe on 3/3/17.
 */
public interface MongoDatastore<T, E, R> {

    E buildEntity(T object);

    R buildResponse(E entity);
}