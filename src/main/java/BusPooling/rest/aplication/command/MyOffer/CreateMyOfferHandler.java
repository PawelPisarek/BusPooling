package BusPooling.rest.aplication.command.MyOffer;

import BusPooling.rest.aplication.command.MyOffer.CreateMyOffer;
import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.service.IService;

/**
 * Created by pawe on 3/3/17.
 */
public class CreateMyOfferHandler implements IHandleCommand<CreateMyOffer> {

    private IService<CreateMyOffer,MyOfferEntity> iService;

    public CreateMyOfferHandler(IService<CreateMyOffer, MyOfferEntity> iService) {
        this.iService = iService;
    }

    @Override
    public void handle(CreateMyOffer command) {
        this.iService.addFromHandle(command);
    }
}
