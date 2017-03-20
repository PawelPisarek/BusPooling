package BusPooling.rest.service;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.Comment.CreateComment;
import BusPooling.rest.domain.Comment;
import BusPooling.rest.infrastructure.DAO.CommentDAO;
import BusPooling.rest.infrastructure.entity.CommentEntity;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.repository.DelayedTransportRepository;
import BusPooling.rest.repository.IRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by pawe on 3/13/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    @Mock
    IRepository repository;
    CommentService commentService;

    @Before
    public void setUp() {
        commentService = new CommentService(repository);
    }

    @Test
    public void getAll_unit() throws Exception {

        final ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        when(repository.getAll()).thenReturn(comments);

        final Collection<Comment> all = commentService.getAll();
        Mockito.verify(repository, Mockito.times(1)).getAll();
        assertEquals(1, all.size());
    }

    @Test
    public void addFromHandle_unit() throws Exception {

        final CreateComment mock = mock(CreateComment.class);
        final Comment value = new Comment();
        when(mock.getComment()).thenReturn(value);
        commentService.addFromHandle(mock);
        verify(repository, Mockito.times(1)).addData(value);
        verify(mock, Mockito.times(1)).getComment();
    }

    @Test
    public void getAll() throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        final IRepository getCommentRepository = context.getBean("getCommentRepository", IRepository.class);
        final CommentService commentService = new CommentService(getCommentRepository);
        final Collection<Comment> all = commentService.getAll();

    }


    @Test
    public void addFromHandle() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        final IRepository getCommentRepository = context.getBean("getCommentRepository", IRepository.class);
        final CommentService commentService = new CommentService(getCommentRepository);
        IRepository delayedTransportRepository = context.getBean("getDelayedTransportRepository", DelayedTransportRepository.class);
        final DelayedTransportEntity byId = (DelayedTransportEntity) delayedTransportRepository.findById("58c564514d4bef6a2582ff24");
        commentService.addFromHandle(new CreateComment(new CommentDAO("1", "", "asasdsadsaddsad"), byId,new ArrayList<String>()));
    }

    @Test
    public void update() throws Exception {

    }

}