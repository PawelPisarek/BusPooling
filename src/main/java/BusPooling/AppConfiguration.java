package BusPooling;

import BusPooling.config.CustomUserDetailsService;
import BusPooling.config.UsersDatabase;
import BusPooling.config.UsersMongoDatabase;
import BusPooling.config.mongo.ConfirmationTokenRepository;
import BusPooling.config.mongo.UserRepository;
import BusPooling.rest.aplication.CommandBus;
import BusPooling.rest.aplication.ICommandBus;
import BusPooling.rest.aplication.command.Comment.CreateCommentHandler;
import BusPooling.rest.aplication.command.DelayedTransport.DelayedTransportHandler;
import BusPooling.rest.aplication.command.DelayedTransport.UpdateDelayedTransportHandler;
import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.aplication.command.MyOffer.CreateMyOfferHandler;
import BusPooling.rest.aplication.command.MyOffer.UpdateMyOfferHandler;
import BusPooling.rest.aplication.command.TransportOffer.CreateTransportOfferHandler;
import BusPooling.rest.aplication.command.TransportOffer.UpdateTransportOfferHandler;
import BusPooling.rest.aplication.command.UserHandler;
import BusPooling.rest.domain.Comment;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.domain.TransportOffer;
import BusPooling.rest.infrastructure.*;
import BusPooling.rest.infrastructure.TransportOffer.DbalTransportOfferQuery;
import BusPooling.rest.infrastructure.TransportOffer.DbalTransportOfferCachedQuery;
import BusPooling.rest.infrastructure.TransportOffer.IDbalTransportOfferQuery;
import BusPooling.rest.infrastructure.entity.CommentEntity;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;
import BusPooling.rest.infrastructure.repository.CommentRepository;
import BusPooling.rest.infrastructure.repository.DelayedTransportRepository;
import BusPooling.rest.infrastructure.repository.MyOfferRepository;
import BusPooling.rest.infrastructure.repository.TransportOfferRepository;
import BusPooling.rest.repository.IRepository;




import BusPooling.rest.service.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashMap;

import static BusPooling.AppConfiguration.Commands.CREATE_DELAYED_TRANSPORT;

/**
 * Created by pawe on 2/27/17.
 */
@Configuration
public class AppConfiguration {

//    public final static String CREATE_USER = "CREATE_USER";
//    public final static String CREATE_DELAYED_TRANSPORT = "CreateDelayedTransport";



    @Autowired
    BusPooling.config.mongo.UserRepository userRepository;

    @Bean
    public BusPooling.rest.repository.UserRepository getRepository() {
        return getEntityManager();

    }
    UserDetailsService userDetailsService;


    @Bean
    public UserDetailsService userDetailsService() {
        if (userDetailsService == null)
            userDetailsService = new CustomUserDetailsService(usersDatabase());
        return userDetailsService;
    }

    @Bean
    public UsersDatabase usersDatabase() {
        return new UsersMongoDatabase( userRepository);
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
    public IRepository<TransportOffer, TransportOfferEntity> getTransportOfferRepository() {
        return this.getRepositories().get(Repositories.TRANSPORT_OFFER);
    }

    @Bean
    public IRepository<Comment, CommentEntity> getCommentRepository() {
        return this.getRepositories().get(Repositories.COMMENT_REPOSITORY);
    }

    @Bean
    public UnitOfWork getUnitOfWork() {
        return new UnitOfWork(this.getRepositories());
    }

    @Bean
    public DbalDelayedTransportQuery getDelayedTransportQuery() {
        return new DbalDelayedTransportQuery(this.getDelayedTransportRepository(), this.mongoClient());
    }

    @Bean
    public DbalMyOfferQuery getMyOfferQuery() {
        return new DbalMyOfferQuery(this.getMyOfferRepository(), this.mongoClient(), this.getDelayedTransportQuery());
    }

    @Bean
    public IDbalTransportOfferQuery getTransportOfferQuery() {
        return new DbalTransportOfferQuery(this.getTransportOfferRepository(), this.mongoClient(), this.getDelayedTransportQuery());
    }

    @Bean
    public IDbalTransportOfferQuery getTransportOfferCachedQuery() {
        return new DbalTransportOfferCachedQuery(this.getTransportOfferQuery());
    }

    @Bean
    public IService getDelayedTransportService() {
        return new DelayedTransportService(this.getDelayedTransportRepository());
    }

    @Bean
    public IService getMyOfferService() {
        return new MyOfferService(this.getMyOfferRepository());
    }

    @Bean
    public IService getCommentService() {
        return new CommentService(this.getCommentRepository());
    }

    @Bean
    public IService getTransportOfferService() {
        return new TransportOfferService(this.getTransportOfferRepository());
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
        handler.put(Commands.CREATE_MY_OFFER, new CreateMyOfferHandler(this.getMyOfferService()));
        handler.put(Commands.UPDATE_MY_OFFER, new UpdateMyOfferHandler(this.getMyOfferService()));
        handler.put(Commands.CREATE_TRANSPORT_OFFER, new CreateTransportOfferHandler(this.getTransportOfferService()));
        handler.put(Commands.UPDATE_TRANSPORT_OFFER, new UpdateTransportOfferHandler(this.getTransportOfferService()));
        handler.put(Commands.CREATE_COMMAND, new CreateCommentHandler(this.getCommentService()));
        return handler;
    }

    @Bean
    public HashMap<Repositories, IRepository> getRepositories() {
        HashMap<Repositories, IRepository> handler = new HashMap<>();
        handler.put(Repositories.DELAYED_TRANSPORT, new DelayedTransportRepository(this.mongoClient()));
        handler.put(Repositories.MY_OFFER, new MyOfferRepository(this.mongoClient()));
        handler.put(Repositories.TRANSPORT_OFFER, new TransportOfferRepository(this.mongoClient()));
        handler.put(Repositories.COMMENT_REPOSITORY, new CommentRepository(this.mongoClient()));
        return handler;
    }

    public enum Commands {
        CREATE_USER,
        CREATE_DELAYED_TRANSPORT,
        UPDATE_DELAYED_TRANSPORT,
        CREATE_MY_OFFER,
        UPDATE_MY_OFFER,
        CREATE_TRANSPORT_OFFER,
        UPDATE_TRANSPORT_OFFER,
        CREATE_COMMAND
    }

    public enum Repositories {
        DELAYED_TRANSPORT, MY_OFFER, TRANSPORT_OFFER, COMMENT_REPOSITORY
    }
}
