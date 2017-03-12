package BusPooling.rest.infrastructure.repository;

import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.User;
import BusPooling.rest.infrastructure.entity.UserEntityMongo;
import BusPooling.rest.repository.IRepository;
import BusPooling.rest.repository.MongoDatastore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by pawe on 3/3/17.
 */
public class DelayedTransportRepository implements IRepository<DelayedTransport, DelayedTransportEntity>, MongoDatastore<DelayedTransport, DelayedTransportEntity, DelayedTransport> {
    Datastore mongoDatabase;
    private Collection<DelayedTransportEntity> listToSave;

    public DelayedTransportRepository(Datastore mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
        this.listToSave = new ArrayList<DelayedTransportEntity>();

    }


    @Override
    public Collection<DelayedTransport> getAll() {
        Collection<DelayedTransport> lists = new ArrayList<DelayedTransport>();

        for (DelayedTransportEntity userEntity : this.mongoDatabase.find(DelayedTransportEntity.class)) {
            lists.add(buildResponse(userEntity));
        }

        return lists;
    }


    public DelayedTransportEntity findById(String id) {
        Collection<DelayedTransportEntity> lists = new ArrayList<DelayedTransportEntity>();

        for (DelayedTransportEntity userEntity : this.mongoDatabase.find(DelayedTransportEntity.class)) {
            lists.add(userEntity);
        }

        return lists.stream().filter(entitty -> {
            return id.equals(buildResponse(entitty).getId());
        }).collect(Collectors.toList()).stream().findAny()
                .orElse(null);
    }

    @Override
    public DelayedTransportEntity buildEntity(DelayedTransport object) {
        return new DelayedTransportEntity(object.getNameTrain(), object.getFrom(), object.getAlternative(), object.getLat(), object.getLng());

    }

    @Override
    public DelayedTransport buildResponse(DelayedTransportEntity entity) {
        return new DelayedTransport(entity.getId().toString(), entity.getNameTrain(), entity.getFrom(), entity.getAlternative(), entity.getLat(), entity.getLng());
    }

    @Override
    public DelayedTransportEntity update(DelayedTransportEntity save) {
        this.listToSave.add(save);
        return save;

    }

    @Override
    public void save() {
        for (DelayedTransportEntity o : this.listToSave) {
            this.mongoDatabase.save(o);
        }
    }

    @Override
    public DelayedTransport addData(DelayedTransport data) {
        DelayedTransportEntity userEntity = buildEntity(data);
        Key<DelayedTransportEntity> userEntityKey = this.mongoDatabase
                .save(userEntity);
        return data;
    }
}
