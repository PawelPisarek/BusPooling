package BusPooling.rest.infrastructure.repository;


import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
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
public class MyOfferRepository extends AbstractRepository implements IRepository<MyOffer, MyOfferEntity>, MongoDatastore<MyOffer, MyOfferEntity, MyOffer> {


    private Collection<MyOfferEntity> listToSave;

    public MyOfferRepository(Datastore mongoDatabase) {
        super(mongoDatabase);
        this.listToSave = new ArrayList<MyOfferEntity>();
    }


    @Override
    public Collection<MyOffer> getAll() {
        Collection<MyOffer> lists = new ArrayList<MyOffer>();

        for (MyOfferEntity userEntity : this.mongoDatabase.find(MyOfferEntity.class)) {
            lists.add(buildResponse(userEntity));
        }

        return lists;
    }

    @Override
    public MyOffer addData(MyOffer data) {
        MyOfferEntity userEntity = buildEntity(data);
        Key<MyOfferEntity> userEntityKey = this.mongoDatabase
                .save(userEntity);
        return data;
    }

    @Override
    public MyOfferEntity findById(String id) {
        Collection<MyOfferEntity> lists = new ArrayList<>();

        for (MyOfferEntity userEntity : this.mongoDatabase.find(MyOfferEntity.class)) {
            lists.add(userEntity);
        }

        return lists.stream().filter(entitty -> {
            return id.equals(buildResponse(entitty).getId());
        }).collect(Collectors.toList()).stream().findAny()
                .orElse(null);
    }

    @Override
    public MyOfferEntity update(MyOfferEntity save) {
        this.listToSave.add(save);
        return save;
    }

    @Override
    public void save() {
        for (MyOfferEntity o : this.listToSave) {
            this.mongoDatabase.save(o);
        }
    }

    @Override
    public MyOfferEntity buildEntity(MyOffer object) {
        return new MyOfferEntity(object.getPrice(), object.getTimeToLeft(), object.getAuthor(), object.getDelayedTransportEntity());
    }

    @Override
    public MyOffer buildResponse(MyOfferEntity entity) {
        return new MyOffer(entity.getId().toString(), entity.getPrice(), entity.getTimeToLeft(), entity.getPersonEntity(), entity.getDelayedTransportEntity());
    }
}
