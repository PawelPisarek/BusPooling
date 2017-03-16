package BusPooling.rest.aplication.command.TransportOffer;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.infrastructure.DAO.TransportOfferDAO;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;

/**
 * Created by pawe on 3/12/17.
 */
public class UpdateTransportOffer implements ICommand {

    TransportOfferDAO transportOffer;
    TransportOfferEntity transportOfferEntity;

    public UpdateTransportOffer(TransportOfferDAO transportOffer, TransportOfferEntity transportOfferEntity) {
        this.transportOffer = transportOffer;
        this.transportOfferEntity = transportOfferEntity;
    }

    public TransportOfferDAO getTransportOffer() {
        return transportOffer;
    }

    public TransportOfferEntity getTransportOfferEntity() {
        return transportOfferEntity;
    }

    @Override
    public AppConfiguration.Commands getKey() {
        return AppConfiguration.Commands.UPDATE_TRANSPORT_OFFER;
    }
}
