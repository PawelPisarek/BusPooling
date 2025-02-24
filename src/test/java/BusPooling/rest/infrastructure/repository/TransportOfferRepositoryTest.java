package BusPooling.rest.infrastructure.repository;

import BusPooling.AppConfiguration;
import BusPooling.rest.domain.TransportOffer;
import BusPooling.rest.repository.IRepository;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by pawe on 3/12/17.
 */
public class TransportOfferRepositoryTest {
    @Test
    public void addData() throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        IRepository testmorphia = new TransportOfferRepository(context.getBean("mongoClien2t", Datastore.class));
        DelayedTransportRepository delayedTransportRepository = new DelayedTransportRepository(context.getBean("mongoClien2t", Datastore.class));
        TransportOffer myOffer = new TransportOffer("asd","uuid","asd","asd","asd",delayedTransportRepository.findById("58c564514d4bef6a2582ff24"));
        testmorphia.addData(myOffer);
    }

}