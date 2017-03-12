package BusPooling;

import BusPooling.rest.aplication.CommandBus;
import BusPooling.rest.aplication.ICommandBus;
import BusPooling.rest.aplication.command.DelayedTransportHandler;
import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.aplication.command.UserHandler;
import BusPooling.rest.aplication.handler.CreateDelayedTransportHandler;
import BusPooling.rest.aplication.handler.UpdateDelayedTransportHandler;
import BusPooling.rest.aplication.handler.UpdateMyOfferHandler;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.infrastructure.*;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.infrastructure.repository.DelayedTransportRepository;
import BusPooling.rest.infrastructure.repository.MyOfferRepository;
import BusPooling.rest.repository.IRepository;
import BusPooling.rest.repository.UserRepository;
import BusPooling.rest.service.DelayedTransportService;
import BusPooling.rest.service.IService;
import BusPooling.rest.service.MyOfferService;
import BusPooling.rest.service.UserService;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
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
    public IRepository<DelayedTransport, DelayedTransportEntity> getDelayedTransportRepository() {
        return this.getRepositories().get(Repositories.DELAYED_TRANSPORT);
    }

    @Bean
    public IRepository<MyOffer, MyOfferEntity> getMyOfferRepository() {
        return this.getRepositories().get(Repositories.MY_OFFER);
    }

    @Bean
    public UnitOfWork getUnitOfWork() {
        return new UnitOfWork(this.getRepositories());
    }

    @Bean
    public DbalDelayedTransportQuery getDelayedTransportQuery() {
        return new DbalDelayedTransportQuery(this.getDelayedTransportRepository());
    }

    @Bean
    public DbalMyOfferQuery getMyOfferQuery() {
        return new DbalMyOfferQuery(this.getMyOfferRepository());
    }

    @Bean
    public IService getDelayedTransportService() {
        return new DelayedTransportService(this.getDelayedTransportRepository());
    }

    @Bean
    public IService getMyOfferService() {
        return new MyOfferService(this.getMyOfferRepository());
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
        handler.put(Commands.UPDATE_DELAYED_TRANSPORT, new UpdateDelayedTransportHandler(this.getDelayedTransportService()));
        handler.put(Commands.CREATE_MY_OFFER, new CreateDelayedTransportHandler(this.getMyOfferService()));
        handler.put(Commands.UPDATE_MY_OFFER, new UpdateMyOfferHandler(this.getMyOfferService()));
        return handler;
    }

    @Bean
    public HashMap<Repositories, IRepository> getRepositories() {
        HashMap<Repositories, IRepository> handler = new HashMap<>();
        handler.put(Repositories.DELAYED_TRANSPORT, new DelayedTransportRepository(this.mongoClient()));
        handler.put(Repositories.MY_OFFER, new MyOfferRepository(this.mongoClient()));
        return handler;
    }

    public enum Commands {
        CREATE_USER, CREATE_DELAYED_TRANSPORT, UPDATE_DELAYED_TRANSPORT, CREATE_MY_OFFER, UPDATE_MY_OFFER
    }

    public enum Repositories {
        DELAYED_TRANSPORT, MY_OFFER
    }
}
