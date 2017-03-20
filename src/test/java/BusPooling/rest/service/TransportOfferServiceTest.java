package BusPooling.rest.service;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.TransportOffer.CreateTransportOffer;
import BusPooling.rest.aplication.command.TransportOffer.UpdateTransportOffer;
import BusPooling.rest.infrastructure.DAO.TransportOfferDAO;
import BusPooling.rest.infrastructure.UnitOfWork;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;
import BusPooling.rest.infrastructure.repository.DelayedTransportRepository;
import BusPooling.rest.repository.IRepository;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by pawe on 3/12/17.
 */
public class TransportOfferServiceTest {
    @Test
    public void addFromHandle() throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        IRepository transportOfferRepository = context.getBean("getTransportOfferRepository", IRepository.class);
        IRepository delayedTransportRepository = context.getBean("getDelayedTransportRepository", DelayedTransportRepository.class);

        HashMap<AppConfiguration.Repositories, IRepository> hashMap = context.getBean("getRepositories", HashMap.class);
        TransportOfferService transportOfferService = new TransportOfferService(transportOfferRepository);
        TransportOfferDAO i = new TransportOfferDAO("ASD", "ASD", "SAD", "SAD","asd");

        final DelayedTransportEntity byId = (DelayedTransportEntity) delayedTransportRepository.findById("58c564514d4bef6a2582ff24");
        CreateTransportOffer command = new CreateTransportOffer(i, byId,new ArrayList<>());

        final UnitOfWork unitOfWork = new UnitOfWork(hashMap);
        unitOfWork.registerRepository(AppConfiguration.Repositories.TRANSPORT_OFFER);
        transportOfferService.addFromHandle(command);
    }

    @Test
    public void getAll() throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        HashMap<AppConfiguration.Repositories, IRepository> hashMap = context.getBean("getRepositories", HashMap.class);
        IRepository transportOfferRepository = context.getBean("getTransportOfferRepository", IRepository.class);
        IService transportOfferService = new TransportOfferService(transportOfferRepository);
        final Collection all = transportOfferService.getAll();


    }

    @Test
    public void findById() throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        IRepository getDelayedTransportRepository = context.getBean("getTransportOfferRepository", IRepository.class);
        TransportOfferService transportOfferService = new TransportOfferService(getDelayedTransportRepository);
        final TransportOfferEntity byId = transportOfferService.findById("58c6a83f4d4bef0c65d3a100");

    }

    @Test
    public void update() throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        IRepository getDelayedTransportRepository = context.getBean("getTransportOfferRepository", IRepository.class);

        HashMap<AppConfiguration.Repositories, IRepository> hashMap = context.getBean("getRepositories", HashMap.class);
        TransportOfferService transportOfferService = new TransportOfferService(getDelayedTransportRepository);
        TransportOfferDAO i = new TransportOfferDAO("ASD", "niska", "SAD", "SAD","asd");
        TransportOfferEntity i2 = transportOfferService.findById("58c5cc3e4d4bef3ee402f6ef");
        final UpdateTransportOffer command = new UpdateTransportOffer(i, i2);
        final UnitOfWork unitOfWork = new UnitOfWork(hashMap);
        unitOfWork.registerRepository(AppConfiguration.Repositories.TRANSPORT_OFFER);
        transportOfferService.update(command);
        unitOfWork.commit();
    }

}