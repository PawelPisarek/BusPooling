package BusPooling.rest.infrastructure.repository;

import BusPooling.AppConfiguration;
import BusPooling.configurations.data.User;
import BusPooling.configurations.repositories.UsersDatabase;
import BusPooling.rest.domain.Person;
import BusPooling.rest.repository.IRepository;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by pawe on 3/15/17.
 */
public class UserRepositoryTest {
    @Test
    public void getUsers() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        UserRepository personRepository = new UserRepository(context.getBean("personEntityIRepository", IRepository.class));
        final List<User> users = personRepository.getUsers();
    }

    @Test
    public void findByLogin() throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        UserRepository personRepository = new UserRepository(context.getBean("personEntityIRepository", IRepository.class));
        final User asd = personRepository.findByLogin("rafal@pydyniak.pl");
    }

}