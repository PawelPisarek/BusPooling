package BusPooling;

import BusPooling.rest.commandBus.CommandBus;
import BusPooling.rest.commandBus.ICommandBus;
import BusPooling.rest.commandBus.IHandleCommand;
import BusPooling.rest.commandBus.UserHandler;
import BusPooling.rest.repository.InMemoryUserRepository;
import BusPooling.rest.repository.UserRepository;
import BusPooling.rest.service.UserService;
import org.springframework.aop.scope.DefaultScopedObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;

/**
 * Created by pawe on 2/27/17.
 */
@Configuration
public class AppConfiguration {

    @Bean
    public UserRepository getRepository() {
        return new InMemoryUserRepository();

    }

    @Bean
    public UserService getUserService() {
        return new UserService(this.getRepository());
    }

    @Bean
    public ICommandBus getCommandBus() {
        return new CommandBus(this.getHandlers());
    }

    private HashMap<String, IHandleCommand> getHandlers() {
        HashMap<String, IHandleCommand> handler = new HashMap<String,IHandleCommand>();

        handler.put("user", new UserHandler(this.getUserService()));
        return handler;
    }
}
