package BusPooling.rest.aplication.command.Person;

import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.domain.Person;
import BusPooling.rest.service.IService;

/**
 * Created by pawe on 3/3/17.
 */
public class UpdatePersonHandler implements IHandleCommand<UpdatePerson> {

    IService<RegisterPerson, UpdatePerson, Person> iService;

    public UpdatePersonHandler(IService<RegisterPerson, UpdatePerson, Person> iService) {
        this.iService = iService;
    }

    @Override
    public void handle(UpdatePerson command) {
        this.iService.update(command);
    }
}
