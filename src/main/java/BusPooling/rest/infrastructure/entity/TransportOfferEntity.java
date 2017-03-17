package BusPooling.rest.infrastructure.entity;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PostLoad;
import org.mongodb.morphia.annotations.Reference;


@Entity("transport-offers")
public class TransportOfferEntity {

    @Id
    ObjectId id;

    private String uuid;
    private String price;
    private String transportName;
    private String seats;
    @Reference
    private DelayedTransportEntity delayedTransportEntity;


    public TransportOfferEntity() {
    }

    public TransportOfferEntity(String uuid, String price, String transportName, String seats, DelayedTransportEntity delayedTransportEntity) {
        this.uuid = uuid;
        this.price = price;
        this.transportName = transportName;
        this.seats = seats;
        this.delayedTransportEntity = delayedTransportEntity;
    }

    @PostLoad
    private void postLoad(DBObject dbObj) {
//        LOGGER.info("postLoad: {}", dbObj);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }


    public DelayedTransportEntity getDelayedTransportEntity() {
        return delayedTransportEntity;
    }

    public void setDelayedTransportEntity(DelayedTransportEntity delayedTransportEntity) {
        this.delayedTransportEntity = delayedTransportEntity;
    }
}
