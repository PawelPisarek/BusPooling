package BusPooling.rest.infrastructure;


import BusPooling.rest.infrastructure.entity.User;
import BusPooling.rest.infrastructure.entity.UserEntityMongo;
import com.mongodb.DBCollection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

/**
 * Created by pawe on 3/2/17.
 */
public class MongoEntityManager {
    Datastore mongoDatabase;

    public MongoEntityManager(Datastore mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    private UserEntityMongo buildUserEntity(User user, boolean active) {
        return new UserEntityMongo(user.getFirstName(), user.getLastName(), active);
    }

    public void AddNewUser(){

        UserEntityMongo userEntity = buildUserEntity(new User("1","nazwa","kast" ), false);
        Key<UserEntityMongo> userEntityKey = this.mongoDatabase
                .save(userEntity);

//        MongoCollection<Document> users = this.mongoDatabase.getCollection("users");

//        users.insertOne(new User("ktos"));
    }

}