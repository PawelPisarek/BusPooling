package BusPooling.rest.aplication.command.TransportOffer;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.infrastructure.DAO.TransportOfferDAO;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;

import static BusPooling.AppConfiguration.Commands.CREATE_TRANSPORT_OFFER;


/**
 * Created by pawe on 3/4/17.
 */
public class CreateTransportOffer implements ICommand {

    TransportOfferDAO myOffer;
    DelayedTransportEntity delayedTransport;

    public CreateTransportOffer(TransportOfferDAO myOffer, DelayedTransportEntity delayedTransport) {
        this.myOffer = myOffer;
        this.delayedTransport = delayedTransport;
    }

    public TransportOfferDAO getMyOffer() {
        return myOffer;
    }

    public DelayedTransportEntity getDelayedTransport() {
        return delayedTransport;
    }

    @Override
    public AppConfiguration.Commands getKey() {
        return CREATE_TRANSPORT_OFFER;
    }
}
