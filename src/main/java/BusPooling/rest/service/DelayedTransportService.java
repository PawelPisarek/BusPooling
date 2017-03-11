package BusPooling.rest.service;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.CreateDelayedTransport;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.repository.IRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DelayedTransportService implements IService<CreateDelayedTransport> {

    private final IRepository<DelayedTransport> delayedTransportIRepository;


    public DelayedTransportService(IRepository<DelayedTransport> userRepository) {
        this.delayedTransportIRepository = userRepository;
    }

    public DelayedTransportService() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.delayedTransportIRepository = context.getBean("getDelayedTransportRepository", IRepository.class);
    }


    public Collection<DelayedTransport> getAll() {
        return this.delayedTransportIRepository.getAll();
    }

    @Override
    public void addFromHandle(CreateDelayedTransport command) {
        this.delayedTransportIRepository.addData(command.getDelayedTransport());
    }
}
