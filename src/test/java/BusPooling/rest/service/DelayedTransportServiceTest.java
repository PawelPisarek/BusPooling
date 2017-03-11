package BusPooling.rest.service;

//import BusPooling.rest.domain.User;

import BusPooling.AppConfiguration;
import BusPooling.rest.infrastructure.entity.User;
import BusPooling.rest.repository.IRepository;
import BusPooling.rest.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DelayedTransportServiceTest {

    @Test
    public void GetAllDelayedTransport() {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        IRepository getDelayedTransportRepository = context.getBean("getDelayedTransportRepository", IRepository.class);
        DelayedTransportService delayedTransportService = new DelayedTransportService(getDelayedTransportRepository);
        delayedTransportService.getAll();
    }


}
