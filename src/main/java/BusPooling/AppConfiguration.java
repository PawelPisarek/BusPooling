package BusPooling;

import BusPooling.rest.aplication.CommandBus;
import BusPooling.rest.aplication.ICommandBus;
import BusPooling.rest.aplication.command.DelayedTransportHandler;
import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.aplication.command.UserHandler;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.infrastructure.DbalDelayedTransportQuery;
import BusPooling.rest.infrastructure.DbalUserQuery;
import BusPooling.rest.infrastructure.MongoEntityManager;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.repository.DelayedTransportRepository;
import BusPooling.rest.repository.IRepository;
import BusPooling.rest.repository.UserRepository;
import BusPooling.rest.service.DelayedTransportService;
import BusPooling.rest.service.IService;
import BusPooling.rest.service.UserService;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

import static BusPooling.AppConfiguration.Commands.CREATE_DELAYED_TRANSPORT;

/**
 * Created by pawe on 2/27/17.
 */
@Configuration
public class AppConfiguration {

//    public final static String CREATE_USER = "CREATE_USER";
//    public final static String CREATE_DELAYED_TRANSPORT = "CreateDelayedTransport";

    @Bean
    public UserRepository getRepository() {
        return getEntityManager();

    }

    @Bean
    public IRepository<DelayedTransport,DelayedTransportEntity> getDelayedTransportRepository() {
        return new DelayedTransportRepository(this.mongoClient());

    }

    @Bean
    public DbalDelayedTransportQuery getDelayedTransportQuery() {
        return new DbalDelayedTransportQuery(this.getDelayedTransportRepository());
    }

    @Bean
    public IService getDelayedTransportService() {
        return new DelayedTransportService(this.getDelayedTransportRepository());
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
    public HashMap<Commands, IHandleCommand> getHandlers() {
        HashMap<Commands, IHandleCommand> handler = new HashMap<>();
        handler.put(Commands.CREATE_USER, new UserHandler(this.getUserService()));
        handler.put(CREATE_DELAYED_TRANSPORT, new DelayedTransportHandler(this.getDelayedTransportService()));
        return handler;
    }

    public enum Commands {
        CREATE_USER, CREATE_DELAYED_TRANSPORT, FIND_DELAYED_TRANSPORT
    }
}
