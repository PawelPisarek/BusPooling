package BusPooling.rest.service;


import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.AcceptedOffer.AcceptOffer;
import BusPooling.rest.aplication.command.AcceptedOffer.UpdateAcceptedOffer;
import BusPooling.rest.aplication.command.Closure.IInformUsers;
import BusPooling.rest.domain.AcceptedOffer;
import BusPooling.rest.infrastructure.entity.AcceptedOfferEntity;
import BusPooling.rest.repository.IRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AcceptedOfferService implements IService<AcceptOffer, UpdateAcceptedOffer, AcceptedOffer> {

    private final IRepository<AcceptedOffer, AcceptedOfferEntity> iRepository;

    public AcceptedOfferService() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.iRepository = context.getBean("getTransportOfferRepository", IRepository.class);
    }

    public AcceptedOfferService(IRepository<AcceptedOffer, AcceptedOfferEntity> iRepository) {
        this.iRepository = iRepository;
    }

    @Override
    public Collection<AcceptedOffer> getAll() {
        return null;
    }

    @Override
    public void addFromHandle(AcceptOffer command) {
        throw new ExceptionInInitializerError("accepted offer not implemented");
    }

    @Override
    public void addFromHandle(AcceptOffer command, IInformUsers informUsers) {

        iRepository.addData(command.getAcceptedOffer());
        informUsers.execute(command);
    }

    @Override
    public void update(UpdateAcceptedOffer command) {

    }
}
