package BusPooling.rest.aplication.command.Person;

import BusPooling.rest.aplication.command.Comment.CreateComment;
import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.domain.Comment;
import BusPooling.rest.domain.Person;
import BusPooling.rest.service.IService;

/**
 * Created by pawe on 3/3/17.
 */
public class RegisterPersonHandler implements IHandleCommand<RegisterPerson> {

    IService<RegisterPerson, UpdatePerson, Person> iService;

    public RegisterPersonHandler(IService<RegisterPerson, UpdatePerson, Person> iService) {
        this.iService = iService;
    }

    @Override
    public void handle(RegisterPerson command) {
        iService.addFromHandle(command);
    }
}
