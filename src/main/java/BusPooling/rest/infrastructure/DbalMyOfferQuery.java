package BusPooling.rest.infrastructure;

import BusPooling.rest.aplication.query.DelayedTransportView.DelayedTransportView;
import BusPooling.rest.aplication.query.MyOfferView.MyOfferView;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.repository.IRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


/**
 * Created by pawe on 3/1/17.
 */
public class DbalMyOfferQuery {

    private final IRepository<MyOffer, MyOfferEntity> repository;
    Datastore mongoDatabase;

    public DbalMyOfferQuery(IRepository<MyOffer, MyOfferEntity> repository, Datastore mongoDatabase) {
        this.repository = repository;
        this.mongoDatabase = mongoDatabase;
    }

    public List<MyOfferView> getAll(String delayedTransportId) {
        final List<DelayedTransportEntity> from = this.mongoDatabase.createQuery(DelayedTransportEntity.class)
                .filter("uuid", delayedTransportId).asList();
        if (from.size() == 0) throw new NotFoundException();
        return this.mongoDatabase.createQuery(MyOfferEntity.class)
                .filter("delayedTransportEntity", from.get(0)).asList()
                .stream().map(entity -> new MyOfferView(
                        entity.getId().toString(),
                        entity.getPrice(),
                        entity.getTimeToLeft(),
                        entity.getAuthor()))
                .collect(Collectors.toList());
    }

    public MyOfferEntity getById(String id) {
        final MyOfferEntity byId = this.repository.findById(id);
        return byId;
    }

}
