package BusPooling.rest.infrastructure;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.ICommandBus;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.UnknownHostException;

/**
 * Created by pawe on 3/2/17.
 */
public class MongoEntityManagerTest {



    @Test
    public void AddUser() {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        MongoEntityManager testmorphia = new MongoEntityManager(context.getBean("mongoClient", Datastore.class));

        testmorphia.AddNewUser();


    }
}
