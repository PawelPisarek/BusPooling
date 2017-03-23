package BusPooling.rest.infrastructure;

import BusPooling.rest.aplication.query.MyOfferView.MyOfferView;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.repository.IRepository;
import org.mongodb.morphia.Datastore;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by pawe on 3/1/17.
 */
public class DbalMyOfferQuery {

    private final IRepository<MyOffer, MyOfferEntity> repository;
    Datastore mongoDatabase;
    DbalDelayedTransportQuery dbalDelayedTransportQuery;

    public DbalMyOfferQuery(IRepository<MyOffer, MyOfferEntity> repository, Datastore mongoDatabase, DbalDelayedTransportQuery dbalDelayedTransportQuery) {
        this.repository = repository;
        this.mongoDatabase = mongoDatabase;
        this.dbalDelayedTransportQuery = dbalDelayedTransportQuery;
    }

    public List<MyOfferView> getAll(String delayedTransportId) {
        final DelayedTransportEntity byUuid = this.dbalDelayedTransportQuery.getByUuid(delayedTransportId);
        return this.mongoDatabase.createQuery(MyOfferEntity.class)
                .filter("delayedTransportEntity", byUuid).asList()
                .stream().map(entity -> new MyOfferView(
                        entity.getId().toString(),
                        entity.getPrice(),
                        entity.getTimeToLeft(),
                        entity.getPersonEntity().getUsername()))
                .collect(Collectors.toList());
    }

    public MyOfferEntity getById(String id) {
        final MyOfferEntity byId = this.repository.findById(id);
        return byId;
    }

}
