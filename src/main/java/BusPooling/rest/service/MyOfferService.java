package BusPooling.rest.service;


import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.CreateMyOffer;
import BusPooling.rest.aplication.command.UpdateMyOffer;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.repository.IRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MyOfferService implements IService<CreateMyOffer, UpdateMyOffer> {

    private final IRepository<MyOffer, MyOfferEntity> iRepository;

    public MyOfferService(IRepository<MyOffer, MyOfferEntity> delayedTransportIRepository) {
        this.iRepository = delayedTransportIRepository;
    }

    public MyOfferService() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.iRepository = context.getBean("getMyOfferRepository", IRepository.class);
    }

    public Collection<MyOffer> getAll() {
        return this.iRepository.getAll();
    }

    public MyOfferEntity findById(String id) {
        final MyOfferEntity delayedTransport = this.iRepository.findById(id);
        return delayedTransport;
    }

    @Override
    public void addFromHandle(CreateMyOffer command) {
        this.iRepository.addData(command.getMyOffer());
    }

    @Override
    public void update(UpdateMyOffer command) {
        final MyOfferEntity myOfferEntity = command.getMyOfferEntity();
        myOfferEntity.setAuthor(command.getMyOffer().getAuthor());
        myOfferEntity.setPrice(command.getMyOffer().getPrice());
        myOfferEntity.setTimeToLeft(command.getMyOffer().getTimeToLeft());
        this.iRepository.update(myOfferEntity);
    }
}
