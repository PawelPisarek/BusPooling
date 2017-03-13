package BusPooling.rest.service;

//import BusPooling.rest.domain.User;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.MyOffer.UpdateMyOffer;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.infrastructure.DAO.MyOfferDAO;
import BusPooling.rest.infrastructure.UnitOfWork;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.repository.IRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;

@RunWith(MockitoJUnitRunner.class)
public class MyOfferServiceTest {

    @Test
    public void Get_all_my_offer() {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        HashMap<AppConfiguration.Repositories, IRepository> hashMap = context.getBean("getRepositories", HashMap.class);
        IRepository getDelayedTransportRepository = context.getBean("getMyOfferRepository", IRepository.class);
        MyOfferService delayedTransportService = new MyOfferService(getDelayedTransportRepository);
        delayedTransportService.getAll();
    }

    @Test
    public void get_by_id_MyOffer() {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        IRepository getDelayedTransportRepository = context.getBean("getMyOfferRepository", IRepository.class);
        MyOfferService delayedTransportService = new MyOfferService(getDelayedTransportRepository);
        final MyOfferEntity byId = delayedTransportService.findById("58c569d14d4bef6fe87457c7");
    }

    @Test
    public void update_MyOffer() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        IRepository getDelayedTransportRepository = context.getBean("getMyOfferRepository", IRepository.class);

        HashMap<AppConfiguration.Repositories, IRepository> hashMap = context.getBean("getRepositories", HashMap.class);
        MyOfferService delayedTransportService = new MyOfferService(getDelayedTransportRepository);
        MyOfferDAO i = new MyOfferDAO("ASD","ASD","SAD","SAD");
        MyOfferEntity i2 = delayedTransportService.findById("58c569d14d4bef6fe87457c7");
        final UpdateMyOffer command = new UpdateMyOffer(i, i2);
        final UnitOfWork unitOfWork = new UnitOfWork(hashMap);
        unitOfWork.registerRepository(AppConfiguration.Repositories.MY_OFFER);
        delayedTransportService.update(command);
        unitOfWork.commit();
    }


}
