package BusPooling.rest.aplication.command.Closure;

import BusPooling.rest.aplication.Intention.UsersConnectedWithDelayedTransport;
import BusPooling.rest.aplication.command.MyOffer.CreateMyOffer;
import BusPooling.rest.service.NotificationService;

/**
 * Created by pawe on 3/18/17.
 */
public class InformUsers implements IInformUsers<UsersConnectedWithDelayedTransport> {


    public NotificationService notificationService;

    public InformUsers(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void execute(UsersConnectedWithDelayedTransport transport) {

        notificationService.informUsers(transport.getAll());
    }
}
