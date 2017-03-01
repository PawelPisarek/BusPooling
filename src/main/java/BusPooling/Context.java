package BusPooling;

import BusPooling.rest.repository.UserRepository;
import BusPooling.rest.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigRegistry;
import org.springframework.stereotype.Component;

@Component
public class Context implements IContext{


    public  static UserService userService;
    public Context() {

    }

    @Override
    public ApplicationContext getAppContext() {
        return new AnnotationConfigApplicationContext(AppConfiguration.class);
    }

    public UserRepository getUserRepository(){
        return new AnnotationConfigApplicationContext(AppConfiguration.class).getBean("getRepository", UserRepository.class);
    }
}
