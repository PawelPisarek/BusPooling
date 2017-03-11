package BusPooling.rest.infrastructure.entity;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Date;


@Entity("delayed-transports")
public class DelayedTransportEntity {

    @Id
    ObjectId id;


    private String nameTrain;


    private String from;
    private String alternative;
    private String lat;
    private String lng;

    public DelayedTransportEntity(String nameTrain, String from, String alternative, String lat, String lng) {
        this.nameTrain = nameTrain;
        this.from = from;
        this.alternative = alternative;
        this.lat = lat;
        this.lng = lng;
    }



    @PostLoad
    private void postLoad(DBObject dbObj) {
//        LOGGER.info("postLoad: {}", dbObj);
    }

    public DelayedTransportEntity() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNameTrain() {
        return nameTrain;
    }

    public void setNameTrain(String nameTrain) {
        this.nameTrain = nameTrain;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
