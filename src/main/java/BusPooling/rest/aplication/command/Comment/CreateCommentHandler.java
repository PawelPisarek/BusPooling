package BusPooling.rest.aplication.command.Comment;

import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.aplication.command.TransportOffer.CreateTransportOffer;
import BusPooling.rest.aplication.command.TransportOffer.UpdateTransportOffer;
import BusPooling.rest.domain.Comment;
import BusPooling.rest.domain.TransportOffer;
import BusPooling.rest.service.IService;

/**
 * Created by pawe on 3/3/17.
 */
public class CreateCommentHandler implements IHandleCommand<CreateComment> {

    private IService<CreateComment, Object, Comment> iService;

    public CreateCommentHandler(IService<CreateComment, Object, Comment> iService) {
        this.iService = iService;
    }

    @Override
    public void handle(CreateComment command) {
        this.iService.addFromHandle(command);
    }
}
