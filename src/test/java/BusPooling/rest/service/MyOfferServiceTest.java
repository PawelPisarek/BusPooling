package BusPooling.rest.service;

//import BusPooling.rest.domain.User;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.MyOffer.UpdateMyOffer;
import BusPooling.rest.domain.MyOffer;
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
        delayedTransportService.findById("58c51b394d4bef3c2f72b51b");
    }

    @Test
    public void update_MyOffer() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        IRepository getDelayedTransportRepository = context.getBean("getMyOfferRepository", IRepository.class);

        HashMap<AppConfiguration.Repositories, IRepository> hashMap = context.getBean("getRepositories", HashMap.class);
        MyOfferService delayedTransportService = new MyOfferService(getDelayedTransportRepository);
        MyOffer i = new MyOffer("ASD","ASD","SAD","SAD");
        MyOfferEntity i2 = delayedTransportService.findById("58c51b394d4bef3c2f72b51b");
        final UpdateMyOffer command = new UpdateMyOffer(i, i2);
        final UnitOfWork unitOfWork = new UnitOfWork(hashMap);
        unitOfWork.registerRepository(AppConfiguration.Repositories.MY_OFFER);
        delayedTransportService.update(command);
        unitOfWork.commit();
    }


}
