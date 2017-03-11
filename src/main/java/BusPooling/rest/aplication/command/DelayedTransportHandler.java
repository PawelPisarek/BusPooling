package BusPooling.rest.aplication.command;

import BusPooling.rest.service.DelayedTransportService;
import BusPooling.rest.service.IService;

/**
 * Created by pawe on 3/3/17.
 */
public class DelayedTransportHandler implements IHandleCommand<CreateDelayedTransport> {

    private IService<CreateDelayedTransport> delayedTransportService;

    public DelayedTransportHandler(IService<CreateDelayedTransport> delayedTransportService) {
        this.delayedTransportService = delayedTransportService;
    }

    @Override
    public void handle(CreateDelayedTransport command) {
        this.delayedTransportService.addFromHandle(command);
    }
}
