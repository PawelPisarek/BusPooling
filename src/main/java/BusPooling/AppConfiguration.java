package BusPooling;

import BusPooling.config.AuthenticationFacade;
import BusPooling.config.IAuthenticationFacade;
import BusPooling.rest.aplication.CommandBus;
import BusPooling.rest.aplication.ICommandBus;
import BusPooling.rest.aplication.command.AcceptedOffer.AcceptOfferHandler;
import BusPooling.rest.aplication.command.Comment.CreateCommentHandler;
import BusPooling.rest.aplication.command.DelayedTransport.DelayedTransportHandler;
import BusPooling.rest.aplication.command.DelayedTransport.UpdateDelayedTransportHandler;
import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.aplication.command.MyOffer.CreateMyOfferHandler;
import BusPooling.rest.aplication.command.MyOffer.UpdateMyOfferHandler;
import BusPooling.rest.aplication.command.Person.RegisterPersonHandler;
import BusPooling.rest.aplication.command.Person.UpdatePersonHandler;
import BusPooling.rest.aplication.command.TransportOffer.CreateTransportOfferHandler;
import BusPooling.rest.aplication.command.TransportOffer.UpdateTransportOfferHandler;
import BusPooling.rest.aplication.command.UserHandler;
import BusPooling.rest.domain.*;
import BusPooling.rest.domain.TransportOffer;
import BusPooling.rest.infrastructure.*;
import BusPooling.rest.infrastructure.DBAL.Person.IDbalPersonQuery;
import BusPooling.rest.infrastructure.DBAL.Person.DbalPersonQuery;
import BusPooling.rest.infrastructure.DBAL.TransportOffer.DbalTransportOfferQuery;
import BusPooling.rest.infrastructure.DBAL.TransportOffer.DbalTransportOfferCachedQuery;
import BusPooling.rest.infrastructure.DBAL.TransportOffer.IDbalTransportOfferQuery;
import BusPooling.rest.infrastructure.entity.*;
import BusPooling.rest.infrastructure.repository.*;
import BusPooling.rest.repository.IRepository;
import BusPooling.rest.repository.UserRepository;
import BusPooling.rest.service.*;
import BusPooling.rest.service.PersonService;
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
    public IAuthenticationFacade getAuthenticationFacade(){
        return new AuthenticationFacade(getPersonQuery());
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
    public IRepository<Person, PersonEntity> personEntityIRepository() {
        return this.getRepositories().get(Repositories.PERSON_REPOSITORY);
    }

    @Bean
    public IRepository<AcceptedOffer, AcceptedOfferEntity> acceptedOfferEntityIRepository() {
        return this.getRepositories().get(Repositories.ACCEPTED_OFFER);
    }

    @Bean
    public UnitOfWork getUnitOfWork() {
        return new UnitOfWork(this.getRepositories());
    }

    @Bean
    public DbalDelayedTransportQuery getDelayedTransportQuery() {
        return new DbalDelayedTransportQuery(this.getDelayedTransportRepository(), this.mongoClien2t());
    }

    @Bean
    public IDbalPersonQuery getPersonQuery() {
        return new DbalPersonQuery(this.mongoClien2t());
    }

    @Bean
    public DbalMyOfferQuery getMyOfferQuery() {
        return new DbalMyOfferQuery(this.getMyOfferRepository(), this.mongoClien2t(), this.getDelayedTransportQuery());
    }

    @Bean
    public IDbalTransportOfferQuery getTransportOfferQuery() {
        return new DbalTransportOfferQuery(this.getTransportOfferRepository(), this.mongoClien2t(), this.getDelayedTransportQuery());
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
    public IService getAcceptedOfferService() {
        return new AcceptedOfferService(this.acceptedOfferEntityIRepository());
    }

    @Bean
    public IService getCommentService() {
        return new CommentService(this.getCommentRepository());
    }

    @Bean
    public IService getTransportOfferService() {
        return new TransportOfferService(this.getTransportOfferRepository());
    }

    @Bean
    public IService getPersonService() {
        return new PersonService(this.personEntityIRepository());
    }


    private static Datastore datastore;

    @Bean
    public Datastore mongoClien2t() {

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
        handler.put(Commands.CREATE_COMMENT, new CreateCommentHandler(this.getCommentService()));
        handler.put(Commands.REGISTER_PERSON, new RegisterPersonHandler(this.getPersonService()));
        handler.put(Commands.UPDATE_PERSON, new UpdatePersonHandler(this.getPersonService()));
        handler.put(Commands.ACCEPT_OFFER, new AcceptOfferHandler(this.getAcceptedOfferService()));
        return handler;
    }

    @Bean
    public HashMap<Repositories, IRepository> getRepositories() {
        HashMap<Repositories, IRepository> handler = new HashMap<>();
        handler.put(Repositories.DELAYED_TRANSPORT, new DelayedTransportRepository(this.mongoClien2t()));
        handler.put(Repositories.MY_OFFER, new MyOfferRepository(this.mongoClien2t()));
        handler.put(Repositories.TRANSPORT_OFFER, new TransportOfferRepository(this.mongoClien2t()));
        handler.put(Repositories.COMMENT_REPOSITORY, new CommentRepository(this.mongoClien2t()));
        handler.put(Repositories.PERSON_REPOSITORY, new PersonRepository(this.mongoClien2t()));
        handler.put(Repositories.ACCEPTED_OFFER, new AcceptedOfferRepository(this.mongoClien2t()));
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
        CREATE_COMMENT,
        REGISTER_PERSON,
        UPDATE_PERSON,
        ACCEPT_OFFER
    }

    public enum Repositories {
        DELAYED_TRANSPORT, MY_OFFER, TRANSPORT_OFFER, COMMENT_REPOSITORY, PERSON_REPOSITORY, ACCEPTED_OFFER
    }
}
