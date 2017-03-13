package BusPooling.rest.aplication.command.TransportOffer;

import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.aplication.command.MyOffer.CreateMyOffer;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.domain.TransportOffer;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;
import BusPooling.rest.service.IService;

/**
 * Created by pawe on 3/3/17.
 */
public class CreateTransportOfferHandler implements IHandleCommand<CreateTransportOffer> {

    private IService<CreateTransportOffer, UpdateTransportOffer, TransportOffer> iService;

    public CreateTransportOfferHandler(IService<CreateTransportOffer, UpdateTransportOffer, TransportOffer> iService) {
        this.iService = iService;
    }

    @Override
    public void handle(CreateTransportOffer command) {
        this.iService.addFromHandle(command);
    }
}
