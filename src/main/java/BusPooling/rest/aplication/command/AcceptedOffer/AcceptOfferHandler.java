package BusPooling.rest.aplication.command.AcceptedOffer;

import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.domain.AcceptedOffer;
import BusPooling.rest.service.IService;

/**
 * Created by pawe on 3/16/17.
 */
public class AcceptOfferHandler implements IHandleCommand<AcceptOffer> {

    IService<AcceptOffer, UpdateAcceptedOffer, AcceptedOffer> iService;

    public AcceptOfferHandler(IService<AcceptOffer, UpdateAcceptedOffer, AcceptedOffer> iService) {
        this.iService = iService;
    }

    @Override
    public void handle(AcceptOffer command) {
        iService.addFromHandle(command);
    }
}
