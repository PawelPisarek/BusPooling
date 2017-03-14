package BusPooling.rest.infrastructure.repository;

import BusPooling.AppConfiguration;
import BusPooling.rest.domain.Comment;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.infrastructure.entity.CommentEntity;
import BusPooling.rest.repository.MongoDatastore;
import BusPooling.rest.repository.UserRepository;
import BusPooling.rest.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by pawe on 3/13/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentRepositoryTest {
    @Test
    public void addData_Integration() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        CommentRepository testmorphia = new CommentRepository(context.getBean("mongoClien2t", Datastore.class));
        DelayedTransportRepository delayedTransportRepository = new DelayedTransportRepository(context.getBean("mongoClien2t", Datastore.class));
        final Comment data = new Comment("sadasdasdsadasd", "asd", "asd", "asd", delayedTransportRepository.findById("58c564514d4bef6a2582ff24"));
        testmorphia.addData(data);


    }
    @Mock
    Datastore datastore;
    CommentRepository commentRepository;

    @Before
    public void setUp() {
        commentRepository = new CommentRepository(datastore);
    }


    @Test
    public void addData_Unit() throws Exception {
        // you have to use Power mock
        final Comment data = new Comment("sadasdasdsadasd", "asd", "asd", "asd", null);
        final CommentEntity mock = mock(CommentEntity.class);
        commentRepository.addData(data);
//        Mockito.verify(datastore,Mockito.times(1)).save(mock);
    }
}