package BusPooling.rest.aplication.command;


import BusPooling.rest.infrastructure.entity.User;
import BusPooling.rest.repository.UserRepository;
import BusPooling.rest.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

/**
 * Created by pawe on 3/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserHandlerTest {

    @Mock
    UserService userService;

    UserHandler userHandler;


    @Before
    public void setUp() {
        userHandler = new UserHandler(userService);
    }

    @Test
    public void handle_command_addUser() {

        User i = mock(User.class);
        userHandler.handle(new CreateNewUser(i));

        Mockito.verify(userService, Mockito.times(1)).addUser(i);

    }
}
