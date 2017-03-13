package BusPooling.rest.infrastructure.entity;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PostLoad;
import org.mongodb.morphia.annotations.Reference;


@Entity("comments")
public class CommentEntity {

    @Id
    ObjectId id;

    private String uuid;
    private String root;
    private String text;
    @Reference
    private DelayedTransportEntity delayedTransportEntity;


    public CommentEntity() {
    }

    public CommentEntity(String uuid, String root, String text, DelayedTransportEntity delayedTransportEntity) {
        this.uuid = uuid;
        this.root = root;
        this.text = text;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DelayedTransportEntity getDelayedTransportEntity() {
        return delayedTransportEntity;
    }

    public void setDelayedTransportEntity(DelayedTransportEntity delayedTransportEntity) {
        this.delayedTransportEntity = delayedTransportEntity;
    }
}
