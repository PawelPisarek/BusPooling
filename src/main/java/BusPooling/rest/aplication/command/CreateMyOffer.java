package BusPooling.rest.aplication.command;

import BusPooling.AppConfiguration;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.domain.MyOffer;

import static BusPooling.AppConfiguration.Commands.CREATE_DELAYED_TRANSPORT;
import static BusPooling.AppConfiguration.Commands.CREATE_MY_OFFER;


/**
 * Created by pawe on 3/4/17.
 */
public class CreateMyOffer implements ICommand {

    MyOffer myOffer;

    public CreateMyOffer(MyOffer myOffer) {
        this.myOffer = myOffer;
    }

    public MyOffer getMyOffer() {
        return myOffer;
    }

    @Override
    public AppConfiguration.Commands getKey() {
        return CREATE_MY_OFFER;
    }
}
