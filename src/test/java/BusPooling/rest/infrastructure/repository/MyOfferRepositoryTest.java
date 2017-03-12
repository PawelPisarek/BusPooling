package BusPooling.rest.infrastructure.repository;

import BusPooling.AppConfiguration;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.domain.MyOffer;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by pawe on 3/12/17.
 */
public class MyOfferRepositoryTest {

    @Test
    public void add_my_offer(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        MyOfferRepository testmorphia = new MyOfferRepository(context.getBean("mongoClient", Datastore.class));
        DelayedTransportRepository delayedTransportRepository = new DelayedTransportRepository(context.getBean("mongoClient", Datastore.class));
        MyOffer myOffer = new MyOffer("sadasdasdsadasd", "asd", "asd", "asd");
        myOffer.setDelayedTransportEntity(delayedTransportRepository.findById("58c564514d4bef6a2582ff24"));
        testmorphia.addData(myOffer);

    }
}
