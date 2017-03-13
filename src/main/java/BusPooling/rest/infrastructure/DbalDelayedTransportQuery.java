package BusPooling.rest.infrastructure;

import BusPooling.rest.aplication.query.Comment.CommentView;
import BusPooling.rest.aplication.query.DelayedTransportView.DelayedTransportDetailView;
import BusPooling.rest.aplication.query.DelayedTransportView.DelayedTransportView;
import BusPooling.rest.aplication.query.MyOfferView.MyOfferView;
import BusPooling.rest.aplication.query.TransportView.TransportOfferView;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.infrastructure.entity.CommentEntity;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;
import BusPooling.rest.repository.IRepository;
import org.mongodb.morphia.Datastore;
import org.springframework.context.annotation.Lazy;

import javax.ws.rs.NotFoundException;
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

    public DelayedTransportDetailView getByUuidDetail(String id) {
        final DelayedTransportEntity byUuid = this.getByUuid(id);
        final List<TransportOfferView> delayedTransportEntity = this.mongoDatabase.createQuery(TransportOfferEntity.class)
                .filter("delayedTransportEntity", byUuid).asList()
                .stream().map(entity -> new TransportOfferView(
                        entity.getId().toString(),
                        entity.getPrice(),
                        entity.getTransportName(),
                        entity.getSeats(),
                        entity.getIsJoined()))
                .collect(Collectors.toList());

        final List<MyOfferView> delayedTransportEntity1 = this.mongoDatabase.createQuery(MyOfferEntity.class)
                .filter("delayedTransportEntity", byUuid).asList()
                .stream().map(entity -> new MyOfferView(
                        entity.getId().toString(),
                        entity.getPrice(),
                        entity.getTimeToLeft(),
                        entity.getAuthor()))
                .collect(Collectors.toList());

        final List<CommentView> delayedTransportEntity2 = this.mongoDatabase.createQuery(CommentEntity.class)
                .filter("delayedTransportEntity", byUuid).asList()
                .stream().map(entity -> new CommentView(
                        entity.getId().toString(),
                        entity.getUuid(),
                        entity.getRoot(),
                        entity.getText()))
                .collect(Collectors.toList());

        return new DelayedTransportDetailView(byUuid.getUuid(),
                byUuid.getNameTrain(),
                byUuid.getFrom(),
                byUuid.getAlternative(),
                byUuid.getLat(),
                byUuid.getLng(),
                delayedTransportEntity,
                delayedTransportEntity1,
                delayedTransportEntity2);
    }


    public DelayedTransportEntity getByUuid(String id) {
        final List<DelayedTransportEntity> byId = this.mongoDatabase.createQuery(DelayedTransportEntity.class)
                .filter("uuid", id).asList();
        if (byId.size() == 0) throw new NotFoundException();
        return byId.get(0);
    }

}
