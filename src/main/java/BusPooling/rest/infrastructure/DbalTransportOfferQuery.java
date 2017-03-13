package BusPooling.rest.infrastructure;

import BusPooling.rest.aplication.query.MyOfferView.MyOfferView;
import BusPooling.rest.aplication.query.TransportView.TransportOfferView;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.domain.TransportOffer;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;
import BusPooling.rest.repository.IRepository;
import org.mongodb.morphia.Datastore;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by pawe on 3/1/17.
 */
public class DbalTransportOfferQuery implements IDbal<TransportOfferView, TransportOfferEntity> {

    private final IRepository<TransportOffer, TransportOfferEntity> repository;
    Datastore mongoDatabase;
    DbalDelayedTransportQuery dbalDelayedTransportQuery;

    public DbalTransportOfferQuery(IRepository<TransportOffer, TransportOfferEntity> repository, Datastore mongoDatabase, DbalDelayedTransportQuery dbalDelayedTransportQuery) {
        this.repository = repository;
        this.mongoDatabase = mongoDatabase;
        this.dbalDelayedTransportQuery = dbalDelayedTransportQuery;
    }

    @Override
    public List<TransportOfferView> getAll(String delayedTransportId) {
        final DelayedTransportEntity byUuid = this.dbalDelayedTransportQuery.getByUuid(delayedTransportId);
        return this.mongoDatabase.createQuery(TransportOfferEntity.class)
                .filter("delayedTransportEntity", byUuid).asList()
                .stream().map(entity -> new TransportOfferView(
                        entity.getId().toString(),
                        entity.getPrice(),
                        entity.getTransportName(),
                        entity.getSeats(),
                        entity.getIsJoined()))
                .collect(Collectors.toList());
    }

    @Override
    public TransportOfferEntity getById(String id) {
        return this.repository.findById(id);
    }

}
