package BusPooling.rest.infrastructure.entity;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PostLoad;
import org.mongodb.morphia.annotations.Reference;


@Entity("my-offers")
public class MyOfferEntity {

    @Id
    ObjectId id;

    private String price;
    private String timeToLeft;
    @Reference
    private PersonEntity personEntity;
    @Reference
    private DelayedTransportEntity delayedTransportEntity;



    public MyOfferEntity() {
    }

    public MyOfferEntity(String price, String timeToLeft, PersonEntity personEntity, DelayedTransportEntity delayedTransportEntity) {
        this.price = price;
        this.timeToLeft = timeToLeft;
        this.personEntity = personEntity;
        this.delayedTransportEntity = delayedTransportEntity;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTimeToLeft() {
        return timeToLeft;
    }

    public void setTimeToLeft(String timeToLeft) {
        this.timeToLeft = timeToLeft;
    }


    public DelayedTransportEntity getDelayedTransportEntity() {
        return delayedTransportEntity;
    }

    public void setDelayedTransportEntity(DelayedTransportEntity delayedTransportEntity) {
        this.delayedTransportEntity = delayedTransportEntity;
    }

    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }
}
