package BusPooling.rest.aplication.command.DelayedTransport;

import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.service.IService;

/**
 * Created by pawe on 3/11/17.
 */
public class UpdateDelayedTransportHandler implements IHandleCommand<UpdateDelayedTransport> {
    private IService<CreateDelayedTransport,UpdateDelayedTransport> delayedTransportService;


    public UpdateDelayedTransportHandler(IService<CreateDelayedTransport,UpdateDelayedTransport> delayedTransportService) {
        this.delayedTransportService = delayedTransportService;
    }


    public void handle(UpdateDelayedTransport command) {

        this.delayedTransportService.update(command);
    }
}
