package BusPooling.rest.aplication.command.AcceptedOffer;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.domain.AcceptedOffer;
import BusPooling.rest.infrastructure.entity.PersonEntity;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;

/**
 * Created by pawe on 3/16/17.
 */
public class AcceptOffer implements ICommand {

    PersonEntity personEntity;
    TransportOfferEntity transportOffer;

    public AcceptOffer(PersonEntity personEntity, TransportOfferEntity transportOffer) {
        this.personEntity = personEntity;
        this.transportOffer = transportOffer;
    }

    public AcceptedOffer getAcceptedOffer() {
        return new AcceptedOffer("none", personEntity, transportOffer);
    }

    @Override
    public AppConfiguration.Commands getKey() {
        return AppConfiguration.Commands.ACCEPT_OFFER;
    }
}
