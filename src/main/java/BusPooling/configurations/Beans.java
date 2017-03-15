package BusPooling.configurations;


import BusPooling.configurations.repositories.CustomUserDetailsService;
import BusPooling.configurations.repositories.SequenceHandler;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by rafal on 4/4/16.
 */
@Configuration
public class Beans {
    private final String MONGO_URI = "mongodb://tasty:tasty123@ds015720.mlab.com:15720/tasty_meeting";
    UsersMongoDatabase usersMongoDatabase;
    MongoDatabase mongoDatabase;
    SequenceHandler sequenceHandler;


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
    UserDetailsService userDetailsService;
    @Bean
    public UserDetailsService userDetailsService() {
        if (userDetailsService == null)
            userDetailsService = new CustomUserDetailsService(usersDatabase());
        return userDetailsService;
    }


    @Bean
    SequenceHandler sequenceHandler() {
        if (sequenceHandler == null) {
            MongoCollection<Document> counters = mongoClient().getCollection("counters");
            sequenceHandler = new SequenceHandler(counters);
        }
        return sequenceHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
