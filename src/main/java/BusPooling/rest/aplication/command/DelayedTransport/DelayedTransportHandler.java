package BusPooling.rest.aplication.command.DelayedTransport;

import BusPooling.rest.aplication.command.DelayedTransport.CreateDelayedTransport;
import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.service.IService;

/**
 * Created by pawe on 3/3/17.
 */
public class DelayedTransportHandler implements IHandleCommand<CreateDelayedTransport> {

    private IService<CreateDelayedTransport,DelayedTransportEntity> delayedTransportService;

    public DelayedTransportHandler(IService<CreateDelayedTransport,DelayedTransportEntity> delayedTransportService) {
        this.delayedTransportService = delayedTransportService;
    }

    @Override
    public void handle(CreateDelayedTransport command) {
        this.delayedTransportService.addFromHandle(command);
    }
}
