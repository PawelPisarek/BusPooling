package BusPooling.rest.infrastructure.DBAL.TransportOffer;

import BusPooling.rest.aplication.query.TransportView.TransportOfferView;
import BusPooling.rest.domain.Person;
import BusPooling.rest.domain.TransportOffer;
import BusPooling.rest.infrastructure.DbalDelayedTransportQuery;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;
import BusPooling.rest.repository.IRepository;
import org.mongodb.morphia.Datastore;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by pawe on 3/1/17.
 */
public class DbalTransportOfferQuery implements IDbalTransportOfferQuery {

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
                        entity.getId().toString(),
                        entity.getPrice(),
                        entity.getTransportName(),
                        entity.getSeats(),
                        false))
                .collect(Collectors.toList());
    }

    @Override
    public TransportOfferEntity getById(String id) {
        return this.repository.findById(id);
    }

    @Override
    public TransportOfferEntity getByUuid(String uuid) {
        final TransportOfferEntity uuid1 = mongoDatabase.find(TransportOfferEntity.class).filter("uuid", uuid)
                .asList().stream().findAny().orElse(null);

        if (uuid == null) throw new NotFoundException();
        return uuid1;

    }
}
