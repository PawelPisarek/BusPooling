package BusPooling.rest.aplication;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.aplication.command.IHandleCommand;

/**
 * Created by pawe on 2/27/17.
 */
public interface ICommandBus {
    void handle(ICommand command);
    void  registerHandler(AppConfiguration.Commands name, IHandleCommand handler);
}
