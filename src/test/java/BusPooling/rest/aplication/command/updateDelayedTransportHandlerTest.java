package BusPooling.rest.aplication.command;


//import BusPooling.rest.infrastructure.entity.User;

import BusPooling.rest.aplication.command.DelayedTransport.CreateDelayedTransport;
import BusPooling.rest.aplication.command.DelayedTransport.UpdateDelayedTransport;
import BusPooling.rest.aplication.command.DelayedTransport.UpdateDelayedTransportHandler;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.service.IService;
//import BusPooling.rest.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

/**
 * Created by pawe on 3/1/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class updateDelayedTransportHandlerTest {

    @Mock
    IService<CreateDelayedTransport, UpdateDelayedTransport,DelayedTransport> userService;

    UpdateDelayedTransportHandler userHandler;


    @Before
    public void setUp() {
        userHandler = new UpdateDelayedTransportHandler(userService);
    }

    @Test
    public void handle_command_updateDelayedTransport() {

        DelayedTransport i = new DelayedTransport("asd", "asd", "sad", "asd", "sad", "sad","1");
        DelayedTransportEntity i2 = new DelayedTransportEntity("asd","sad","asd","sad","sad","1");
        final UpdateDelayedTransport command = new UpdateDelayedTransport(i, i2);
        userHandler.handle(command);

        Mockito.verify(userService, Mockito.times(1)).update(command);

    }


}
