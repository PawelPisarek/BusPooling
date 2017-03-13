package BusPooling.rest.aplication.command.MyOffer;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.infrastructure.DAO.MyOfferDAO;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;

import static BusPooling.AppConfiguration.Commands.UPDATE_DELAYED_TRANSPORT;


/**
 * Created by pawe on 3/4/17.
 */
public class UpdateMyOffer implements ICommand {
    MyOfferDAO myOffer;
    MyOfferEntity myOfferEntity;

    public UpdateMyOffer(MyOfferDAO myOffer, MyOfferEntity myOfferEntity) {
        this.myOffer = myOffer;
        this.myOfferEntity = myOfferEntity;
    }

    public MyOfferDAO getMyOffer() {
        return myOffer;
    }

    public MyOfferEntity getMyOfferEntity() {
        return myOfferEntity;
    }

    @Override
    public AppConfiguration.Commands getKey() {
        return AppConfiguration.Commands.UPDATE_MY_OFFER;
    }
}
