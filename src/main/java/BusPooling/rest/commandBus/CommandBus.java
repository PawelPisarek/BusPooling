package BusPooling.rest.commandBus;

import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by pawe on 2/27/17.
 */

@Service
public class CommandBus implements ICommandBus {

    private  HashMap<String,IHandleCommand> handlers;

    public CommandBus(HashMap<String, IHandleCommand> handlers) {
        System.out.println(handlers);
        this.handlers = handlers;
    }

    @Override
    public void handle(ICommand command) {
        handlers.get(command.getKey()).handle(command);

    }

    @Override
    public void registerHandler(String name, IHandleCommand handler) {

        this.handlers.put(name, handler);
    }

}
