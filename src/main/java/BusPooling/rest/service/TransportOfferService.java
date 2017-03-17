package BusPooling.rest.service;


import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.TransportOffer.CreateTransportOffer;
import BusPooling.rest.aplication.command.TransportOffer.UpdateTransportOffer;
import BusPooling.rest.domain.TransportOffer;
import BusPooling.rest.infrastructure.DAO.TransportOfferDAO;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;
import BusPooling.rest.repository.IRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TransportOfferService implements IService<CreateTransportOffer, UpdateTransportOffer, TransportOffer> {

    private final IRepository<TransportOffer, TransportOfferEntity> iRepository;

    public TransportOfferService(IRepository<TransportOffer, TransportOfferEntity> delayedTransportIRepository) {
        this.iRepository = delayedTransportIRepository;
    }

    public TransportOfferService() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.iRepository = context.getBean("getTransportOfferRepository", IRepository.class);
    }

    public Collection<TransportOffer> getAll() {
        return this.iRepository.getAll();
    }

    public TransportOfferEntity findById(String id) {
        final TransportOfferEntity delayedTransport = this.iRepository.findById(id);
        return delayedTransport;
    }

    @Override
    public void addFromHandle(CreateTransportOffer command) {
        TransportOfferDAO myOffer = command.getMyOffer();
        final TransportOffer transportOffer = new TransportOffer(myOffer.getUuid(),myOffer.getUuid(),
                myOffer.getPrice(),
                myOffer.getTransportName(),
                myOffer.getSeats(),
                command.getDelayedTransport());
        this.iRepository.addData(transportOffer);
    }

    @Override
    public void update(UpdateTransportOffer command) {
        final TransportOfferEntity transportOfferEntity = command.getTransportOfferEntity();
        final TransportOfferDAO transportOffer = command.getTransportOffer();
        transportOfferEntity.setPrice(transportOffer.getPrice() != null ? transportOffer.getPrice() : transportOfferEntity.getPrice());
        transportOfferEntity.setSeats(transportOffer.getSeats() != null ? transportOffer.getSeats() : transportOfferEntity.getSeats());
        transportOfferEntity.setTransportName(transportOffer.getTransportName() != null ? transportOffer.getTransportName() : transportOfferEntity.getTransportName());
        this.iRepository.update(transportOfferEntity);
    }
}
