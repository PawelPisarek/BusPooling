package BusPooling;

import BusPooling.rest.repository.UserRepository;
import BusPooling.rest.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigRegistry;

/**
 * Created by pawe on 2/27/17.
 */
public interface IContext {
    ApplicationContext getAppContext();
    UserRepository getUserRepository();
}
