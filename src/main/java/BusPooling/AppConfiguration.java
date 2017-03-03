package BusPooling;

import BusPooling.rest.aplication.CommandBus;
import BusPooling.rest.aplication.ICommandBus;
import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.aplication.command.UserHandler;
import BusPooling.rest.infrastructure.DbalUserQuery;
import BusPooling.rest.infrastructure.InMemoryUserRepository;
import BusPooling.rest.infrastructure.MongoEntityManager;
import BusPooling.rest.infrastructure.entity.UserEntityMongo;
import BusPooling.rest.repository.UserRepository;
import BusPooling.rest.service.UserService;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pawe on 2/27/17.
 */
@Configuration
public class AppConfiguration {

    public final static String user = "user";

    @Bean
    public UserRepository getRepository() {
        return getEntityManager();

    }


    private static Datastore datastore;

    @Bean
    public Datastore mongoClient() {

        MongoClientURI mongoClientURI = new MongoClientURI(MONGO_URI);
        MongoClient mongoClient;
        try {
            mongoClient = new MongoClient(mongoClientURI);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        // http://stackoverflow.com/questions/6832517/how-to-check-from-a-driver-if-mongodb-server-is-running
        mongoClient.listDatabaseNames();

        Morphia morphia = new Morphia();

        new ValidationExtension(morphia);

        datastore = morphia.createDatastore(mongoClient,
                mongoClientURI.getDatabase());
        return datastore;
    }



    private final String MONGO_URI = "mongodb://tasty:tasty123@ds113650.mlab.com:13650/testmorphia";
    @Bean
    public MongoEntityManager getEntityManager() {
        return new MongoEntityManager(AppConfiguration.datastore);

    }

    @Bean
    public UserService getUserService() {
        return new UserService(this.getRepository());
    }

    @Bean
    public ICommandBus getCommandBus() {
        return new CommandBus(this.getHandlers());
    }

    @Bean
    public DbalUserQuery getUserQuery() {
        return new DbalUserQuery(this.getRepository());
    }


    @Bean
    public HashMap<String, IHandleCommand> getHandlers() {
        HashMap<String, IHandleCommand> handler = new HashMap<>();
        handler.put(user, new UserHandler(this.getUserService()));
        return handler;
    }
}
