package BusPooling.rest.service;

import BusPooling.AppConfiguration;
import BusPooling.rest.domain.User;
import BusPooling.rest.repository.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Scope(BeanDefinition.SCOPE_SINGLETON)
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserService() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.userRepository = context.getBean("getRepository", UserRepository.class);
    }


    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User addUser(User user) {
        this.userRepository.addUser(user);
        return user;
    }

}
