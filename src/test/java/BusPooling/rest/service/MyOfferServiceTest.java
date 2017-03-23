package BusPooling.rest.service;

//import BusPooling.rest.domain.User;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.Closure.InformUsers;
import BusPooling.rest.aplication.command.MyOffer.CreateMyOffer;
import BusPooling.rest.aplication.command.MyOffer.UpdateMyOffer;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.infrastructure.DAO.MyOfferDAO;
import BusPooling.rest.infrastructure.UnitOfWork;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.repository.IRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyOfferServiceTest {
    @Mock
    IRepository repository;
    @Mock
    NotificationService notificationService;
    MyOfferService commentService;

    @Before
    public void setUp() {
        commentService = new MyOfferService(repository);
    }
    @Test
    public void addFromHandle_unit() throws Exception {

        final CreateMyOffer mock = mock(CreateMyOffer.class);
        final MyOffer value = new MyOffer();
        when(mock.addMyOffer()).thenReturn(value);
        final List<String> cos = new ArrayList<>();
        cos.add("dowolnyTekst");

        commentService.addFromHandle(mock,new InformUsers(notificationService));
        verify(repository, Mockito.times(1)).addData(value);
        verify(mock, Mockito.times(1)).addMyOffer();
        verify(notificationService,Mockito.times(1)).informUsers(any());

    }

    @Test
    public void Get_all_my_offer() {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        HashMap<AppConfiguration.Repositories, IRepository> hashMap = context.getBean("getRepositories", HashMap.class);
        IRepository getDelayedTransportRepository = context.getBean("getMyOfferRepository", IRepository.class);
        MyOfferService delayedTransportService = new MyOfferService(getDelayedTransportRepository);
        final Collection<MyOffer> all = delayedTransportService.getAll();
    }

    @Test
    public void get_by_id_MyOffer() {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        IRepository getDelayedTransportRepository = context.getBean("getMyOfferRepository", IRepository.class);
        MyOfferService delayedTransportService = new MyOfferService(getDelayedTransportRepository);
        final MyOfferEntity byId = delayedTransportService.findById("58d385a2cefef11813969dc6");
    }

    @Test
    public void update_MyOffer() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        IRepository getDelayedTransportRepository = context.getBean("getMyOfferRepository", IRepository.class);

        HashMap<AppConfiguration.Repositories, IRepository> hashMap = context.getBean("getRepositories", HashMap.class);
        MyOfferService delayedTransportService = new MyOfferService(getDelayedTransportRepository);
        MyOfferDAO i = new MyOfferDAO("ASD","ASD","SAD");
        MyOfferEntity i2 = delayedTransportService.findById("58d385a2cefef11813969dc6");
        final UpdateMyOffer command = new UpdateMyOffer(i, i2);
        final UnitOfWork unitOfWork = new UnitOfWork(hashMap);
        unitOfWork.registerRepository(AppConfiguration.Repositories.MY_OFFER);
        delayedTransportService.update(command);
        unitOfWork.commit();
    }


}
