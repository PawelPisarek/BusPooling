package BusPooling.rest.infrastructure;

import BusPooling.rest.domain.User;
import BusPooling.rest.repository.UserRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by pawe on 3/1/17.
 */
public class InMemoryUserRepositoryTest {

    @Test
    public void GetUsers_Returns_All_Items() {
        //arrange
        UserRepository i = mock(UserRepository.class);

        List<User> objects = new ArrayList<>();
        objects.add(new User("ktos"));

        when(i.getUsers()).thenReturn(objects);
        //act
        UserRepository ktos = new BusPooling.rest.infrastructure.InMemoryUserRepository();
        ktos.addUser(new User("ktos"));
        List<User> result = ktos.getUsers();
        //assert
        assertEquals(1, result.size());

    }
}
