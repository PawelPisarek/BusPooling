package BusPooling.configurations.repositories;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * Created by rafal on 4/9/16.
 */
public class SequenceHandler {
    MongoCollection<Document> sequenceCollection;

    public SequenceHandler(MongoCollection<Document> mongoCollection) {
        sequenceCollection = mongoCollection;
    }

    public int getNextSequence(String name) {
        Integer seq = sequenceCollection.find(new Document("_id", name)).first().getInteger("seq");
        sequenceCollection.updateOne(new Document("_id", name),
                new Document("$set", new Document("seq", seq+1)));
        return seq;
    }


}
