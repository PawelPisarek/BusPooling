package BusPooling;

import BusPooling.rest.aplication.CommandBus;
import BusPooling.rest.aplication.ICommandBus;
import BusPooling.rest.aplication.command.IHandleCommand;
import BusPooling.rest.aplication.command.UserHandler;
import BusPooling.rest.infrastructure.DbalUserQuery;
import BusPooling.rest.infrastructure.InMemoryUserRepository;
import BusPooling.rest.repository.UserRepository;
import BusPooling.rest.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;

/**
 * Created by pawe on 2/27/17.
 */
@Configuration
public class AppConfiguration {

    public final static String user = "user";

    @Bean
    public UserRepository getRepository() {
        return new InMemoryUserRepository();

    }

    @Bean
    @Scope("singleton")
    public UserService getUserService() {
        return new UserService(this.getRepository());
    }

    @Bean
    public ICommandBus getCommandBus() {
        return new CommandBus(this.getHandlers());
    }

    @Bean
    public DbalUserQuery getUserQuery() {
        return new DbalUserQuery(this.getRepository());
    }


    @Bean
    public HashMap<String, IHandleCommand> getHandlers() {
        HashMap<String, IHandleCommand> handler = new HashMap<>();
        handler.put(user, new UserHandler(this.getUserService()));
        return handler;
    }
}
