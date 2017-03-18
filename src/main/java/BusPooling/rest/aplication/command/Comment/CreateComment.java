package BusPooling.rest.aplication.command.Comment;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.Intention.UsersConnectedWithDelayedTransport;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.domain.Comment;
import BusPooling.rest.infrastructure.DAO.CommentDAO;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;

import java.util.List;

import static BusPooling.AppConfiguration.Commands.CREATE_COMMENT;


/**
 * Created by pawe on 3/4/17.
 */
public class CreateComment implements ICommand,UsersConnectedWithDelayedTransport {

    CommentDAO commentDAO;
    DelayedTransportEntity delayedTransport;
    List<String> personList;

    public CreateComment(CommentDAO commentDAO, DelayedTransportEntity delayedTransport, List<String> personList) {
        this.commentDAO = commentDAO;
        this.delayedTransport = delayedTransport;
        this.personList = personList;
    }

    public CommentDAO getCommentDAO() {
        return commentDAO;
    }

    public Comment getComment() {
        return new Comment(commentDAO.getUuid(), commentDAO.getUuid(), commentDAO.getRoot(), commentDAO.getText(), delayedTransport);
    }

    public DelayedTransportEntity getDelayedTransport() {
        return delayedTransport;
    }

    @Override
    public AppConfiguration.Commands getKey() {
        return CREATE_COMMENT;
    }

    @Override
    public List<String> getAll() {
        return personList;
    }
}
