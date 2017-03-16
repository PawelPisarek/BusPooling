package BusPooling.rest.infrastructure.entity;

import BusPooling.rest.domain.TransportOffer;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PostLoad;
import org.mongodb.morphia.annotations.Reference;


@Entity("accepted-offer")
public class AcceptedOfferEntity {

    @Id
    ObjectId id;

    @Reference
    private PersonEntity personEntity;

    @Reference
    private TransportOfferEntity transportOffer;


    public AcceptedOfferEntity() {
    }

    public AcceptedOfferEntity(PersonEntity personEntity, TransportOfferEntity transportOffer) {
        this.personEntity = personEntity;
        this.transportOffer = transportOffer;
    }

    @PostLoad
    private void postLoad(DBObject dbObj) {
//        LOGGER.info("postLoad: {}", dbObj);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }

    public TransportOfferEntity getTransportOffer() {
        return transportOffer;
    }

    public void setTransportOffer(TransportOfferEntity transportOffer) {
        this.transportOffer = transportOffer;
    }
}
