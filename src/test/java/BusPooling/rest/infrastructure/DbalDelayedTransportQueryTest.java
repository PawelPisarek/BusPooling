package BusPooling.rest.infrastructure;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.query.TransportView.TransportOfferView;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.domain.TransportOffer;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.PersonEntity;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;
import BusPooling.rest.infrastructure.repository.PersonRepository;
import BusPooling.rest.repository.IRepository;
import BusPooling.rest.repository.MongoDatastore;
import BusPooling.rest.service.TransportOfferService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by pawe on 3/16/17.
 */
public class DbalDelayedTransportQueryTest {
    @Test
    public void getTransportOffersWithAuthor() throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        IRepository<DelayedTransport, DelayedTransportEntity> delayedTransportEntityIRepository = context.getBean("getDelayedTransportRepository", IRepository.class);
        Datastore mongoDatastore = context.getBean("mongoClien2t", Datastore.class);
        final DbalDelayedTransportQuery dbalDelayedTransportQuery = new DbalDelayedTransportQuery(delayedTransportEntityIRepository, mongoDatastore);
        final DelayedTransportEntity byId = delayedTransportEntityIRepository.findById("58c564514d4bef6a2582ff24");
        PersonRepository personRepository = new PersonRepository(context.getBean("mongoClien2t", Datastore.class));
        final PersonEntity all = personRepository.findById("58c9b722e15488780817ebba");


        final List<TransportOfferView> transportOffersWithAuthor = dbalDelayedTransportQuery.getTransportOffersWithAuthor(byId, all);
        Assert.assertEquals(false, transportOffersWithAuthor.get(0).isJoined());
        Assert.assertEquals(true, transportOffersWithAuthor.get(1).isJoined());
    }

}