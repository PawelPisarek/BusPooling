package BusPooling.rest.aplication.command.TransportOffer;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.Intention.UsersConnectedWithDelayedTransport;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.domain.TransportOffer;
import BusPooling.rest.infrastructure.DAO.TransportOfferDAO;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;

import java.util.List;

import static BusPooling.AppConfiguration.Commands.CREATE_TRANSPORT_OFFER;


/**
 * Created by pawe on 3/4/17.
 */
public class CreateTransportOffer implements ICommand, UsersConnectedWithDelayedTransport {

    TransportOfferDAO myOffer;
    DelayedTransportEntity delayedTransport;
    List<String> personList;

    public CreateTransportOffer(TransportOfferDAO myOffer, DelayedTransportEntity delayedTransport, List<String> personList) {
        this.myOffer = myOffer;
        this.delayedTransport = delayedTransport;
        this.personList = personList;
    }

    public TransportOfferDAO getMyOffer() {
        return myOffer;
    }

    public DelayedTransportEntity getDelayedTransport() {
        return delayedTransport;
    }

    public TransportOffer transportOffer() {

        TransportOfferDAO myOffer = getMyOffer();
        return new TransportOffer(myOffer.getUuid(), myOffer.getUuid(),
                myOffer.getPrice(),
                myOffer.getTransportName(),
                myOffer.getSeats(),
                delayedTransport);
    }

    @Override
    public List<String> getAll() {
        return personList;
    }

    @Override
    public AppConfiguration.Commands getKey() {
        return CREATE_TRANSPORT_OFFER;
    }
}
