package BusPooling.rest.infrastructure.repository;

import BusPooling.AppConfiguration;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.infrastructure.MongoEntityManager;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by pawe on 3/3/17.
 */
public class DelayedTransportRepositoryTest {


    @Test
    public void AddDelayedTransport() {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        DelayedTransportRepository testmorphia = new DelayedTransportRepository(context.getBean("mongoClient", Datastore.class));

        testmorphia.addData(new DelayedTransport("id","sa222d","asd","asd","asd","sad","1"));


    }




}
