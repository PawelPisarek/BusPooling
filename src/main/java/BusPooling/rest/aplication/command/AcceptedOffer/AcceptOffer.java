package BusPooling.rest.aplication.command.AcceptedOffer;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.Intention.UsersConnectedWithDelayedTransport;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.domain.AcceptedOffer;
import BusPooling.rest.infrastructure.entity.PersonEntity;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;

import java.util.List;

/**
 * Created by pawe on 3/16/17.
 */
public class AcceptOffer implements ICommand,UsersConnectedWithDelayedTransport {

    PersonEntity personEntity;
    TransportOfferEntity transportOffer;
    List<String> personList;

    public AcceptOffer(PersonEntity personEntity, TransportOfferEntity transportOffer, List<String> personList) {
        this.personEntity = personEntity;
        this.transportOffer = transportOffer;
        this.personList = personList;
    }

    public AcceptedOffer getAcceptedOffer() {
        return new AcceptedOffer("none", personEntity, transportOffer);
    }

    @Override
    public AppConfiguration.Commands getKey() {
        return AppConfiguration.Commands.ACCEPT_OFFER;
    }

    @Override
    public List<String> getAll() {
        return personList;
    }
}
