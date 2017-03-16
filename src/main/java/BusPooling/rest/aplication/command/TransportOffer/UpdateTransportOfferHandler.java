package BusPooling.rest.aplication.command.TransportOffer;

import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.domain.TransportOffer;
import BusPooling.rest.service.IService;

/**
 * Created by pawe on 3/11/17.
 */
public class UpdateTransportOfferHandler implements IHandleCommand<UpdateTransportOffer> {
    private IService<CreateTransportOffer, UpdateTransportOffer, TransportOffer> delayedTransportService;

    public UpdateTransportOfferHandler(IService<CreateTransportOffer, UpdateTransportOffer, TransportOffer> delayedTransportService) {
        this.delayedTransportService = delayedTransportService;
    }

    @Override
    public void handle(UpdateTransportOffer command) {
        this.delayedTransportService.update(command);
    }
}
