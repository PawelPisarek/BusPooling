package BusPooling.rest.infrastructure;

import BusPooling.rest.aplication.query.DelayedTransportView.DelayedTransportView;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.repository.IRepository;
import org.mongodb.morphia.Datastore;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by pawe on 3/1/17.
 */
public class DbalDelayedTransportQuery {

    private final IRepository<DelayedTransport, DelayedTransportEntity> repository;
    Datastore mongoDatabase;

    public DbalDelayedTransportQuery(IRepository<DelayedTransport, DelayedTransportEntity> repository, Datastore mongoDatabase) {
        this.repository = repository;
        this.mongoDatabase = mongoDatabase;
    }

    public List<DelayedTransportView> getAll() {
        return this.repository.getAll().stream()
                .map(delayedTransport -> new DelayedTransportView(delayedTransport.getUuid(), delayedTransport.getNameTrain(), delayedTransport.getFrom(), delayedTransport.getAlternative(), delayedTransport.getLat(), delayedTransport.getLng()))
                .collect(Collectors.toList());
    }

    public DelayedTransportEntity getById(String id) {
        final DelayedTransportEntity byId = this.repository.findById(id);
        return byId;
    }

    public DelayedTransportEntity getByUuid(String id) {
        final DelayedTransportEntity byId = this.mongoDatabase.createQuery(DelayedTransportEntity.class)
                .filter("uuid", id).asList().get(0);
        return byId;
    }

}
