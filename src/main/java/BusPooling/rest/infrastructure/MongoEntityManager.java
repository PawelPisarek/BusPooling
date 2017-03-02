package BusPooling.rest.infrastructure;


import BusPooling.rest.domain.User;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.LinkedHashMap;

/**
 * Created by pawe on 3/2/17.
 */
public class MongoEntityManager {
    MongoDatabase mongoDatabase;

    public MongoEntityManager(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }



    public void AddNewUser(){


        MongoCollection<Document> users = this.mongoDatabase.getCollection("users");
        Document put = new Document().append("name", "ktos");
        users.insertOne(put);

//users.save(new User("koasd"));
//        users.insertOne(new User("ktos"));
    }

}