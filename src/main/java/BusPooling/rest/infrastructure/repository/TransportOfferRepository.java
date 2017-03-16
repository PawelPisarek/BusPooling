package BusPooling.rest.infrastructure.repository;


import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.domain.TransportOffer;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;
import BusPooling.rest.repository.IRepository;
import BusPooling.rest.repository.MongoDatastore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by pawe on 3/3/17.
 */
public class TransportOfferRepository extends AbstractRepository implements IRepository<TransportOffer, TransportOfferEntity>, MongoDatastore<TransportOffer, TransportOfferEntity, TransportOffer> {


    private Collection<TransportOfferEntity> listToSave;

    public TransportOfferRepository(Datastore mongoDatabase) {
        super(mongoDatabase);
        this.listToSave = new ArrayList<TransportOfferEntity>();
    }


    @Override
    public Collection<TransportOffer> getAll() {
        Collection<TransportOffer> lists = new ArrayList<TransportOffer>();

        for (TransportOfferEntity userEntity : this.mongoDatabase.find(TransportOfferEntity.class)) {
            lists.add(buildResponse(userEntity));
        }

        return lists;
    }

    @Override
    public TransportOffer addData(TransportOffer data) {
        TransportOfferEntity userEntity = buildEntity(data);
        Key<TransportOfferEntity> userEntityKey = this.mongoDatabase
                .save(userEntity);
        return data;
    }

    @Override
    public TransportOfferEntity buildEntity(TransportOffer object) {
        final TransportOfferEntity transportOfferEntity = new TransportOfferEntity(object.getUuid(), object.getPrice(), object.getTransportName(), object.getSeats(), object.getIsJoined(), object.getDelayedTransportEntity());
        transportOfferEntity.setDelayedTransportEntity(object.getDelayedTransportEntity());
        return transportOfferEntity;
    }

    @Override
    public TransportOfferEntity findById(String id) {
        Collection<TransportOfferEntity> lists = new ArrayList<>();

        for (TransportOfferEntity userEntity : this.mongoDatabase.find(TransportOfferEntity.class)) {
            lists.add(userEntity);
        }

        final TransportOfferEntity transportOfferEntity = lists.stream().filter(entitty -> {
            return id.equals(buildResponse(entitty).getId());
        }).collect(Collectors.toList()).stream().findAny()
                .orElse(null);
        if (transportOfferEntity == null) throw new NotFoundException();
        return transportOfferEntity;
    }

    @Override
    public TransportOffer buildResponse(TransportOfferEntity entity) {
        return new TransportOffer(entity.getId().toString(), entity.getUuid(), entity.getPrice(), entity.getTransportName(), entity.getSeats(), entity.getIsJoined(), entity.getDelayedTransportEntity());
    }

    @Override
    public TransportOfferEntity update(TransportOfferEntity save) {
        this.listToSave.add(save);
        return save;
    }

    @Override
    public void save() {
        for (TransportOfferEntity o : this.listToSave) {
            this.mongoDatabase.save(o);
        }
    }
}
