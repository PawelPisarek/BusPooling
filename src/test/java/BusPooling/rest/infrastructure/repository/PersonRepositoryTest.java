package BusPooling.rest.infrastructure.repository;

import BusPooling.AppConfiguration;
import BusPooling.rest.domain.Person;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by pawe on 3/15/17.
 */
public class PersonRepositoryTest {
    @Test
    public void getAll() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        PersonRepository personRepository = new PersonRepository(context.getBean("mongoClien2t", Datastore.class));
        final Collection<Person> all = personRepository.getAll();
    }

    @Test
    public void addData() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        PersonRepository personRepository = new PersonRepository(context.getBean("mongoClien2t", Datastore.class));
        final Date birthdate = new Date();
        final Person all = personRepository.addData(new Person("1","rafal@pydyniak.pl","password01","asd","asd", birthdate,"sad",false,"sad","asd","asd"));
    }

}