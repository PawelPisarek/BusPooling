package BusPooling.rest.aplication.command.TransportOffer;

import BusPooling.rest.aplication.command.Closure.InformUsers;
import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.domain.TransportOffer;
import BusPooling.rest.service.IService;
import BusPooling.rest.service.NotificationService;

/**
 * Created by pawe on 3/3/17.
 */
public class CreateTransportOfferHandler implements IHandleCommand<CreateTransportOffer> {

    private IService<CreateTransportOffer, UpdateTransportOffer, TransportOffer> iService;
    private NotificationService notificationService;

    public CreateTransportOfferHandler(IService<CreateTransportOffer, UpdateTransportOffer, TransportOffer> iService, NotificationService notificationService) {
        this.iService = iService;
        this.notificationService = notificationService;
    }

    @Override
    public void handle(CreateTransportOffer command) {
        this.iService.addFromHandle(command, new InformUsers(notificationService));
    }
}
