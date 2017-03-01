package BusPooling.rest.commandBus;

/**
 * Created by pawe on 2/27/17.
 */
public interface IHandleCommand<T> {
    void handle(T command);
}
