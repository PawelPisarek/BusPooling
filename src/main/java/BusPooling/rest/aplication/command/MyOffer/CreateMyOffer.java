package BusPooling.rest.aplication.command.MyOffer;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;

import static BusPooling.AppConfiguration.Commands.CREATE_DELAYED_TRANSPORT;
import static BusPooling.AppConfiguration.Commands.CREATE_MY_OFFER;


/**
 * Created by pawe on 3/4/17.
 */
public class CreateMyOffer implements ICommand {

    MyOffer myOffer;
    DelayedTransportEntity delayedTransport;

    public CreateMyOffer(MyOffer myOffer, DelayedTransportEntity delayedTransport) {
        this.myOffer = myOffer;
        this.delayedTransport = delayedTransport;
    }

    public MyOffer getMyOffer() {
        return myOffer;
    }

    public DelayedTransportEntity getDelayedTransportEntity() {
        return delayedTransport;
    }

    @Override
    public AppConfiguration.Commands getKey() {
        return CREATE_MY_OFFER;
    }
}
