package BusPooling.configurations.repositories;


import BusPooling.configurations.data.Role;
import BusPooling.configurations.data.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by rafal on 4/4/16.
 */
public class UsersMongoDatabase implements UsersDatabase {
    MongoCollection<Document> usersCollection;
    @Autowired
    SequenceHandler sequenceHandler;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UsersMongoDatabase(MongoCollection<Document> mongoCollection) {
        usersCollection = mongoCollection;
    }

    @Override
    public User findByLogin(String username) {
        User user = buildUserFromDocument(usersCollection.find(new Document("username", username)).first());
        return user;
    }

    @Override
    public User findById(int id) {
        return buildUserFromDocument(usersCollection.find(new Document("_id", id)).first());
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (Document userEntity : usersCollection.find()) {
            users.add(buildUserFromDocument(userEntity));
        }
        return users;
    }

    @Override
    public User getUser(String id) {
        Document userEntity = usersCollection.find(eq("_id", new ObjectId(id))).first();
        if (userEntity != null) {
            return buildUserFromDocument(userEntity);
        }
        return null;
    }

    private User buildUserFromDocument(Document userEntity) {
        return new User(
                Optional.ofNullable(userEntity.get("_id")).orElse("").toString(),
                Optional.ofNullable(userEntity.get("username")).orElse("").toString(),
                Optional.ofNullable(userEntity.get("password")).orElse("").toString(),
                Optional.ofNullable(userEntity.get("birthdate")).orElse("").toString(),
                Optional.ofNullable(userEntity.get("gender")).orElse("").toString(),
                (ArrayList)userEntity.get("interests"),
                (ArrayList)userEntity.get("culinaryPreferences"),
                (Boolean)userEntity.get("active")
        );
    }

    @Override
    public void addUser(User user) {
//        user.setPassword(getEncodedPassword(user.getPassword()));
        user.addRole(new Role(0, "ROLE_USER"));
        usersCollection.insertOne(
                buildUserEntity(user)
        );
    }

    @Override
    public void updateUser(String id, User user) {
        Document updatedUser = buildUserEntity(user);
        usersCollection.findOneAndReplace(eq("_id", new ObjectId(id)),
                updatedUser);
    }

    @Override
    public void deleteUser(String id) {
        Document userEntity = usersCollection.find(eq("_id", new ObjectId(id))).first();
        usersCollection.deleteOne(userEntity);
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private Document buildUserEntity(User user) {
        return new Document()
                .append("username", user.getUsername())
                .append("password", user.getPassword())
                .append("roles", parseRolesToArray(user.getRoles()))
                .append("birthdate", user.getBirthdate())
                .append("gender", user.getGender())
                .append("interests", user.getInterests())
                .append("culinaryPreferences", user.getCulinaryPreferences())
                .append("active", user.isActive());
    }

  /*  private int getNextSequence() {
        return sequenceHandler.getNextSequence("userid");
    }*/

    private List<String> parseRolesToArray(Set<Role> roles) {
        List<String> rolesList = new ArrayList<>();
        for (Role role : roles) {
            rolesList.add(role.getAuthority());
        }
        return rolesList;
    }

}
