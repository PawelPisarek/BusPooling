package BusPooling.rest.aplication.command.Person;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.domain.Comment;
import BusPooling.rest.domain.Person;
import BusPooling.rest.infrastructure.DAO.RegisterPersonDAO;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;

import static BusPooling.AppConfiguration.Commands.CREATE_COMMENT;
import static BusPooling.AppConfiguration.Commands.REGISTER_PERSON;


/**
 * Created by pawe on 3/4/17.
 */
public class RegisterPerson implements ICommand {

    RegisterPersonDAO commentDAO;

    public RegisterPerson(RegisterPersonDAO commentDAO) {

        this.commentDAO = commentDAO;
    }

    public RegisterPersonDAO getCommentDAO() {
        return commentDAO;
    }

    public Person getRegisteringPerson() {
        return new Person("none", commentDAO.getUsername(), commentDAO.getPassword(), null, null, null, null, false, null, null, null);
    }

    @Override
    public AppConfiguration.Commands getKey() {
        return REGISTER_PERSON;
    }
}