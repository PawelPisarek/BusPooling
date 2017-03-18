package BusPooling.rest.aplication.command.MyOffer;

import BusPooling.rest.aplication.command.Closure.InformUsers;
import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.service.IService;
import BusPooling.rest.service.NotificationService;

/**
 * Created by pawe on 3/3/17.
 */
public class CreateMyOfferHandler implements IHandleCommand<CreateMyOffer> {

    private IService<CreateMyOffer, MyOfferEntity, MyOffer> myOfferIService;
    private NotificationService notificationService;

    public CreateMyOfferHandler(IService<CreateMyOffer, MyOfferEntity, MyOffer> myOfferIService, NotificationService notificationService) {
        this.myOfferIService = myOfferIService;
        this.notificationService = notificationService;
    }

    @Override
    public void handle(CreateMyOffer command) {
        this.myOfferIService.addFromHandle(command, new InformUsers(notificationService));
    }
}
