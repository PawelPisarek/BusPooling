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
        return new InMemoryUserRepository();

    }

    MongoDatabase mongoDatabase;


    private static final String HOST = "ds113650.mlab.com";
    private static final int PORT = 13650;
    private static final String DATABASE = "testmorphia";
    private static final String USER_NAME = "tasty";
    private static final String PASSWORD = "tasty123";
    private static Datastore datastore;

    @Bean
    public Datastore mongoClient() {
        if (datastore == null) {
            Morphia morphia = new Morphia();
            morphia.map(UserEntityMongo.class);
            MongoClient client = createMongoClient();
            datastore = morphia.createDatastore(client, DATABASE);
        }

        return datastore;
    }
    private static MongoClient createMongoClient() {
        MongoCredential credential = MongoCredential.createMongoCRCredential(
                USER_NAME,
                DATABASE,
                PASSWORD.toCharArray()
        );
        List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
        credentialsList.add(credential);

        try {
            ServerAddress serverAddress = new ServerAddress(HOST, PORT);
            return new MongoClient(serverAddress, credentialsList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
