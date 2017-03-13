package BusPooling.rest.aplication.command.MyOffer;

import BusPooling.rest.aplication.command.*;
import BusPooling.rest.aplication.command.MyOffer.CreateMyOffer;
import BusPooling.rest.aplication.command.MyOffer.UpdateMyOffer;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.service.IService;

/**
 * Created by pawe on 3/11/17.
 */
public class UpdateMyOfferHandler implements IHandleCommand<UpdateMyOffer> {
    private IService<CreateMyOffer, UpdateMyOffer, MyOffer> delayedTransportService;


    public UpdateMyOfferHandler(IService<CreateMyOffer, UpdateMyOffer, MyOffer> delayedTransportService) {
        this.delayedTransportService = delayedTransportService;
    }


    public void handle(UpdateMyOffer command) {

        this.delayedTransportService.update(command);
    }
}
