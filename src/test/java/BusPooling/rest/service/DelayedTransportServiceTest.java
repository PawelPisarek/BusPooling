package BusPooling.rest.service;

//import BusPooling.rest.domain.User;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.DelayedTransport.UpdateDelayedTransport;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.infrastructure.UnitOfWork;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.repository.IRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DelayedTransportServiceTest {

    @Test
    public void GetAllDelayedTransport() {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        IRepository getDelayedTransportRepository = context.getBean("getDelayedTransportRepository", IRepository.class);
        DelayedTransportService delayedTransportService = new DelayedTransportService(getDelayedTransportRepository);
        final Collection<DelayedTransport> all = delayedTransportService.getAll();
    }

    @Test
    public void get_by_id_DelayedTransport() {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        IRepository getDelayedTransportRepository = context.getBean("getDelayedTransportRepository", IRepository.class);
        DelayedTransportService delayedTransportService = new DelayedTransportService(getDelayedTransportRepository);
        final DelayedTransportEntity byId = delayedTransportService.findById("58c564514d4bef6a2582ff24");
    }

    @Test
    public void update_DelayedTransport() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        IRepository getDelayedTransportRepository = context.getBean("getDelayedTransportRepository", IRepository.class);

        HashMap<AppConfiguration.Repositories, IRepository> hashMap = context.getBean("getRepositories", HashMap.class);
        DelayedTransportService delayedTransportService = new DelayedTransportService(getDelayedTransportRepository);
        DelayedTransport i = new DelayedTransport("dowolne id nie ma znaczenia jakie", "123213", "Warszawa", "asd", "sad", "sad","2");
        DelayedTransportEntity i2 = delayedTransportService.findById("58c564514d4bef6a2582ff24");
        final UpdateDelayedTransport command = new UpdateDelayedTransport(i, i2);
        final UnitOfWork unitOfWork = new UnitOfWork(hashMap);
        unitOfWork.registerRepository(AppConfiguration.Repositories.DELAYED_TRANSPORT);
        delayedTransportService.update(command);
        unitOfWork.commit();
    }


}
