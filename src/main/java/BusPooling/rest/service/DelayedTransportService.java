package BusPooling.rest.service;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.CreateDelayedTransport;
import BusPooling.rest.aplication.command.UpdateDelayedTransport;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.repository.IRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DelayedTransportService implements IService<CreateDelayedTransport,UpdateDelayedTransport> {

    private final IRepository<DelayedTransport,DelayedTransportEntity> delayedTransportIRepository;


    public DelayedTransportService(IRepository<DelayedTransport,DelayedTransportEntity> userRepository) {
        this.delayedTransportIRepository = userRepository;
    }

    public DelayedTransportService() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.delayedTransportIRepository = context.getBean("getDelayedTransportRepository", IRepository.class);
    }


    public Collection<DelayedTransport> getAll() {
        return this.delayedTransportIRepository.getAll();
    }

    public DelayedTransportEntity findById(String id) {
        final DelayedTransportEntity delayedTransport = this.delayedTransportIRepository.findById(id);
        return delayedTransport;
    }

    @Override
    public void update(UpdateDelayedTransport command) {
        final DelayedTransport delayedTransport = command.getDelayedTransport();
        DelayedTransportEntity delayedTransportEntity = command.getDelayedTransportEntity();
        delayedTransportEntity.setAlternative(delayedTransport.getAlternative());
        delayedTransportEntity.setFrom(delayedTransport.getFrom());
        delayedTransportEntity.setLat(delayedTransport.getLat());
        delayedTransportEntity.setLng(delayedTransport.getLng());
        delayedTransportEntity.setNameTrain(delayedTransport.getNameTrain());

        this.delayedTransportIRepository.save(delayedTransportEntity);
    }

    @Override
    public void addFromHandle(CreateDelayedTransport command) {
        this.delayedTransportIRepository.addData(command.getDelayedTransport());
    }
}
