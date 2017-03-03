package BusPooling.rest.service;

//import BusPooling.rest.domain.User;
import BusPooling.rest.infrastructure.entity.User;
import BusPooling.rest.repository.UserRepository;
import BusPooling.rest.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Iterator;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Test
    public void iterator_will_return_hello_world() {
        //arrange
        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello").thenReturn("World");
        //act
        String result = i.next() + " " + i.next();
        //assert
        assertEquals("Hello World", result);
    }

    @Mock
    UserRepository userRepository;

    UserService userService;

    @Before
    public void setUp() {

        userService = new UserService(userRepository);
    }

    @Test
    public void getUsers_should_getUsers() {

        userService.getUsers();

        Mockito.verify(userRepository, Mockito.times(1)).getUsers();
    }

    @Test
    public void addUser_should_addUser() {

        User user = new User("ktos","cos","jakos");

        userService.addUser(user);

        Mockito.verify(userRepository, Mockito.times(1)).addUser(user);
    }


    @Test
    public void addUser_should_has_name() {

        User user = new User("ktos","cos","jakos");
        userService.addUser(user);
        ArgumentCaptor<User> emailCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository, times(1)).addUser(emailCaptor.capture());
        Assert.assertEquals("cos", emailCaptor.getValue().getFirstName());
    }
    @Test
    public void User_should_has_name() {

        User user = new User("ktos","cos","jakos");
        userService.addUser(user);
        Mockito.when(userService.addUser(any())).thenAnswer(new Answer<User>() {
            public User answer(InvocationOnMock invocation) throws Throwable {
                return new User(
                        (String) invocation.getArguments()[0],
                        (String) invocation.getArguments()[1],
                        (String) invocation.getArguments()[2]
                );
            }
        });
    }





}
