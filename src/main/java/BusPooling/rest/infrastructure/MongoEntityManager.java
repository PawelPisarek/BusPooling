package BusPooling.rest.infrastructure;


import BusPooling.rest.infrastructure.entity.User;
import BusPooling.rest.infrastructure.entity.UserEntityMongo;
import BusPooling.rest.repository.UserRepository;
import com.mongodb.DBCollection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by pawe on 3/2/17.
 */
public class MongoEntityManager  implements UserRepository {
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

    }


    @Override
    public Collection<User> getUsers() {
        Collection<User> lists = new ArrayList<User>();

        for (UserEntityMongo userEntity : this.mongoDatabase.find(UserEntityMongo.class)) {
            lists.add(buildUserResponse(userEntity));
        }

        return lists;
    }
    private User buildUserResponse(UserEntityMongo userEntity) {
        return new User(userEntity.getId().toString(), userEntity.getFirstName(), userEntity.getLastName());
    }



    @Override
    public User addUser(User user) {
        return null;
    }

}