package BusPooling.rest.service;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.AcceptedOffer.AcceptOffer;
import BusPooling.rest.domain.Person;
import BusPooling.rest.domain.TransportOffer;
import BusPooling.rest.infrastructure.entity.PersonEntity;
import BusPooling.rest.repository.IRepository;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

/**
 * Created by pawe on 3/16/17.
 */
public class AcceptedOfferServiceTest {
    @Test
    public void addFromHandle() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        final AcceptedOfferService acceptedOfferService = new AcceptedOfferService(context.getBean("acceptedOfferEntityIRepository", IRepository.class));
        final IRepository<Person, PersonEntity> personEntityIRepository = context.getBean("personEntityIRepository", IRepository.class);
        final PersonEntity byId = personEntityIRepository.findById("58ca4c17e154881e230c1520");
        final IRepository<TransportOffer, BusPooling.rest.infrastructure.entity.TransportOfferEntity> getTransportOfferRepository = context.getBean("getTransportOfferRepository", IRepository.class);
        final BusPooling.rest.infrastructure.entity.TransportOfferEntity byId1 = getTransportOfferRepository.findById("58c6a83f4d4bef0c65d3a100");
        acceptedOfferService.addFromHandle(new AcceptOffer(byId, byId1,new ArrayList<String>()));
    }

    @Test
    public void update() throws Exception {

    }

}