package BusPooling.configurations.repositories;


import BusPooling.configurations.data.Role;
import BusPooling.configurations.data.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by rafal on 4/4/16.
 */
public class UsersMongoDatabase implements UsersDatabase {
    MongoCollection<Document> usersCollection;

    public UsersMongoDatabase(MongoCollection<Document> mongoCollection) {
        usersCollection = mongoCollection;
        long count = usersCollection.count();
        addUser(new User("1", "test", "Test123", "password"));
        System.out.println("test");
    }

    @Override
    public User findByLogin(String username) {
        return new User("1", "test", "test123", "password");
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (Document userEntity : usersCollection.find()) {
            users.add(buildUserFromDocument(userEntity));
        }
        return users;
    }

    private User buildUserFromDocument(Document userEntity) {
        return new User(
                Optional.ofNullable(userEntity.get("_id")).orElse("").toString(),
                Optional.ofNullable(userEntity.get("name")).orElse("").toString(),
                Optional.ofNullable(userEntity.get("login")).orElse("").toString(),
                Optional.ofNullable(userEntity.get("password")).orElse("").toString()
        );
    }

    @Override
    public void addUser(User user) {
        usersCollection.insertOne(
                buildUserEntity(user)
        );
    }

    private Document buildUserEntity(User user) {
        return new Document()
                .append("name", user.getName())
                .append("password", user.getPassword());
//                .append("roles", Arrays.asList(
//
//                ));
    }

    private Document buildRolesEntity(Set<Role> roles) {
//        return new Document()
//                .append("")
        return null;
    }
}
