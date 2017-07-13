package BusPooling.rest.infrastructure;

import BusPooling.rest.aplication.query.Comment.CommentView;
import BusPooling.rest.aplication.query.DelayedTransportView.DelayedTransportDetailView;
import BusPooling.rest.aplication.query.DelayedTransportView.DelayedTransportView;
import BusPooling.rest.aplication.query.MyOfferView.MyOfferView;
import BusPooling.rest.aplication.query.Person.PersonView;
import BusPooling.rest.aplication.query.TransportView.TransportOfferView;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.domain.Person;
import BusPooling.rest.infrastructure.entity.*;
import BusPooling.rest.repository.IRepository;
import com.mongodb.MapReduceCommand;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.MapReduceOptions;
import org.mongodb.morphia.MapreduceResults;
import org.mongodb.morphia.MapreduceType;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
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

    public DelayedTransportDetailView getByUuidDetail(String id, PersonEntity personEntity) {
        final DelayedTransportEntity byUuid = this.getByUuid(id);
        final List<TransportOfferView> delayedTransportEntity = this.getTransportOffersWithAuthor(byUuid, personEntity);
        final List<MyOfferView> delayedTransportEntity1 = this.getMyOffers(byUuid);
        final List<CommentView> delayedTransportEntity2 = this.getComments(byUuid);
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

    public List<MyOfferView> getMyOffers(DelayedTransportEntity delayedTransportEntity) {
        return this.getMyOffersEntity(delayedTransportEntity)
                .stream().map(entity -> {
                    final PersonView personEntity = new PersonView(entity.getPersonEntity().getUsername(),
                            entity.getPersonEntity().getPassword(),
                            entity.getPersonEntity().getName(),
                            entity.getPersonEntity().getSurname(),
                            entity.getPersonEntity().getBirthday(),
                            entity.getPersonEntity().getGender(),
                            entity.getPersonEntity().isActive(),
                            entity.getPersonEntity().getFacebookUID(),
                            entity.getPersonEntity().getGeoLat(),
                            entity.getPersonEntity().getGeoLng());
                    return new MyOfferView(
                            entity.getId().toString(),
                            entity.getPrice(),
                            entity.getTimeToLeft(),
                            personEntity);
                })
                .collect(Collectors.toList());
    }

    public List<String> getAllUserFromMyOffers(DelayedTransportEntity delayedTransportEntity) {
        return this.getMyOffersEntity(delayedTransportEntity)
                .stream().map(myOfferEntity -> {
                    return myOfferEntity.getPersonEntity().getUsername();
                })
                .collect(Collectors.toList());
    }

    public List<MyOfferEntity> getMyOffersEntity(DelayedTransportEntity delayedTransportEntity) {
        return this.mongoDatabase.createQuery(MyOfferEntity.class)
                .filter("delayedTransportEntity", delayedTransportEntity).asList();
    }

    public List<TransportOfferView> getTransportOffersWithAuthor(DelayedTransportEntity delayedTransportEntity, PersonEntity person) {

        final List<TransportOfferView> collect = this.mongoDatabase.createQuery(TransportOfferEntity.class)
                .filter("delayedTransportEntity", delayedTransportEntity).asList()
                .stream().map(entity -> {

                    final AcceptedOfferEntity transportOffer1 = this.mongoDatabase.createQuery(AcceptedOfferEntity.class)
                            .filter("transportOffer", entity).asList().stream().filter(acceptedOfferEntity -> {
                                return acceptedOfferEntity.getPersonEntity().getUsername().equals(person.getUsername());
                            }).collect(Collectors.toList()).stream().findAny().orElse(null);
                    boolean transportOffer = false;
                    if (transportOffer1 != null) transportOffer = true;
                    return new TransportOfferView(
                            entity.getId().toString(),
                            entity.getUuid(),
                            entity.getPrice(),
                            entity.getTransportName(),
                            entity.getSeats(), transportOffer);
                })
                .collect(Collectors.toList());
        return collect;

    }

    public List<CommentView> getComments(DelayedTransportEntity delayedTransportEntity) {
        return this.mongoDatabase.createQuery(CommentEntity.class)
                .filter("delayedTransportEntity", delayedTransportEntity).asList()
                .stream().map(entity -> new CommentView(
                        entity.getId().toString(),
                        entity.getUuid(),
                        entity.getRoot(),
                        entity.getText()))
                .collect(Collectors.toList());
    }
}
