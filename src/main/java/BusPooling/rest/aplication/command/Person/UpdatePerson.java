package BusPooling.rest.aplication.command.Person;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.infrastructure.DAO.PersonDAO;
import BusPooling.rest.infrastructure.entity.PersonEntity;

/**
 * Created by pawe on 3/12/17.
 */
public class UpdatePerson implements ICommand {

    PersonDAO personDAO;
    PersonEntity personEntity;

    public UpdatePerson(PersonDAO transportOffer, PersonEntity transportOfferEntity) {
        this.personDAO = transportOffer;
        this.personEntity = transportOfferEntity;
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    @Override
    public AppConfiguration.Commands getKey() {
        return AppConfiguration.Commands.UPDATE_PERSON;
    }
}
