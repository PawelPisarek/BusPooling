package BusPooling.rest.aplication.command.AcceptedOffer;

import BusPooling.rest.aplication.command.Closure.InformUsers;
import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.domain.AcceptedOffer;
import BusPooling.rest.service.IService;
import BusPooling.rest.service.NotificationService;

/**
 * Created by pawe on 3/16/17.
 */
public class AcceptOfferHandler implements IHandleCommand<AcceptOffer> {

    IService<AcceptOffer, UpdateAcceptedOffer, AcceptedOffer> acceptedOfferIService;
    private NotificationService notificationService;

    public AcceptOfferHandler(IService<AcceptOffer, UpdateAcceptedOffer, AcceptedOffer> acceptedOfferIService, NotificationService notificationService) {
        this.acceptedOfferIService = acceptedOfferIService;
        this.notificationService = notificationService;
    }

    @Override
    public void handle(AcceptOffer command) {
        acceptedOfferIService.addFromHandle(command, new InformUsers(notificationService));
    }
}
