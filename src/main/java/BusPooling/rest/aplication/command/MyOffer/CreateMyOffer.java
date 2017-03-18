package BusPooling.rest.aplication.command.MyOffer;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.Intention.UsersConnectedWithDelayedTransport;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.infrastructure.DAO.MyOfferDAO;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;

import java.util.List;

import static BusPooling.AppConfiguration.Commands.CREATE_MY_OFFER;


/**
 * Created by pawe on 3/4/17.
 */
public class CreateMyOffer implements ICommand, UsersConnectedWithDelayedTransport {

    MyOfferDAO myOffer;
    DelayedTransportEntity delayedTransport;
    List<String> personList;

    public CreateMyOffer(MyOfferDAO myOffer, DelayedTransportEntity delayedTransport, List<String> personList) {
        this.myOffer = myOffer;
        this.delayedTransport = delayedTransport;
        this.personList = personList;
    }

    public MyOfferDAO getMyOffer() {
        return myOffer;
    }

    public DelayedTransportEntity getDelayedTransportEntity() {
        return delayedTransport;
    }


    public MyOffer addMyOffer() {

        return new MyOffer(myOffer.getId(), myOffer.getPrice(), myOffer.getTimeToLeft(), myOffer.getAuthor(), getDelayedTransportEntity());
    }

    @Override
    public AppConfiguration.Commands getKey() {
        return CREATE_MY_OFFER;
    }

    @Override
    public List<String> getAll() {
        return personList;
    }
}
