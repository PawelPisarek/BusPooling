package BusPooling.rest.aplication.command.Comment;

import BusPooling.rest.aplication.command.Closure.InformUsers;
import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.domain.Comment;
import BusPooling.rest.service.IService;
import BusPooling.rest.service.NotificationService;

/**
 * Created by pawe on 3/3/17.
 */
public class CreateCommentHandler implements IHandleCommand<CreateComment> {

    private IService<CreateComment, Object, Comment> commentIService;
    private NotificationService notificationService;

    public CreateCommentHandler(IService<CreateComment, Object, Comment> commentIService, NotificationService notificationService) {
        this.commentIService = commentIService;
        this.notificationService = notificationService;
    }

    @Override
    public void handle(CreateComment command) {
        this.commentIService.addFromHandle(command, new InformUsers(notificationService));
    }
}
