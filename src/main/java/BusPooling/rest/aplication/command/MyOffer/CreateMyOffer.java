package BusPooling.rest.aplication.command.MyOffer;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.infrastructure.DAO.MyOfferDAO;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;

import static BusPooling.AppConfiguration.Commands.CREATE_MY_OFFER;


/**
 * Created by pawe on 3/4/17.
 */
public class CreateMyOffer implements ICommand {

    MyOfferDAO myOffer;
    DelayedTransportEntity delayedTransport;

    public CreateMyOffer(MyOfferDAO myOffer, DelayedTransportEntity delayedTransport) {
        this.myOffer = myOffer;
        this.delayedTransport = delayedTransport;
    }

    public MyOfferDAO getMyOffer() {
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
