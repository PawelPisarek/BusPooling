package BusPooling.rest.commandBus;

/**
 * Created by pawe on 2/27/17.
 */
public interface ICommandBus {
    void handle(ICommand command);
    void  registerHandler(String name,IHandleCommand handler);
}
