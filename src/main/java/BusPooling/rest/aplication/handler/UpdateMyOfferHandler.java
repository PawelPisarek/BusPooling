package BusPooling.rest.aplication.handler;

import BusPooling.rest.aplication.command.*;
import BusPooling.rest.service.IService;

/**
 * Created by pawe on 3/11/17.
 */
public class UpdateMyOfferHandler implements IHandleCommand<UpdateMyOffer> {
    private IService<CreateMyOffer, UpdateMyOffer> delayedTransportService;


    public UpdateMyOfferHandler(IService<CreateMyOffer, UpdateMyOffer> delayedTransportService) {
        this.delayedTransportService = delayedTransportService;
    }


    public void handle(UpdateMyOffer command) {

        this.delayedTransportService.update(command);
    }
}
