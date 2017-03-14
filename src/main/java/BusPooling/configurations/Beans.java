package BusPooling.configurations;


import BusPooling.configurations.repositories.CustomUserDetailsService;
import BusPooling.configurations.repositories.UsersDatabase;
import BusPooling.configurations.repositories.UsersMongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by rafal on 4/4/16.
 */
@Configuration
public class Beans {
    private final String MONGO_URI = "mongodb://tasty:tasty123@ds015720.mlab.com:15720/tasty_meeting";
    UsersMongoDatabase usersMongoDatabase;
    MongoDatabase mongoDatabase;

    @Bean
    public MongoDatabase mongoClient() {
        if (mongoDatabase == null) {
            MongoClient mongoClient = new MongoClient(new MongoClientURI(MONGO_URI));
            mongoDatabase = mongoClient.getDatabase("tasty_meeting");
        }
        return mongoDatabase;
    }

    @Bean
    public UsersDatabase usersDatabase() {
        if (usersMongoDatabase == null) {
            MongoCollection<Document> users = mongoClient().getCollection("users");
            usersMongoDatabase = new UsersMongoDatabase(users);
        }
        return usersMongoDatabase;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(usersDatabase());
    }
}
