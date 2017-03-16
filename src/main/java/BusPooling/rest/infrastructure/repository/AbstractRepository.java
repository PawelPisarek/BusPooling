package BusPooling.rest.infrastructure.repository;

import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import org.mongodb.morphia.Datastore;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pawe on 3/12/17.
 */
public abstract class AbstractRepository {

    public Datastore mongoDatabase;

    public AbstractRepository(Datastore mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }
}
