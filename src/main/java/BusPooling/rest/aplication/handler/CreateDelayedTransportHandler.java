package BusPooling.rest.aplication.handler;

import BusPooling.rest.aplication.command.CreateDelayedTransport;
import BusPooling.rest.aplication.command.CreateMyOffer;
import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.service.IService;

/**
 * Created by pawe on 3/3/17.
 */
public class CreateDelayedTransportHandler implements IHandleCommand<CreateMyOffer> {

    private IService<CreateMyOffer,MyOfferEntity> iService;

    public CreateDelayedTransportHandler(IService<CreateMyOffer, MyOfferEntity> iService) {
        this.iService = iService;
    }

    @Override
    public void handle(CreateMyOffer command) {
        this.iService.addFromHandle(command);
    }
}
