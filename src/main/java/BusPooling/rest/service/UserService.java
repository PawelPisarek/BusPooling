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
    public static int liczbaSingletonów = 0;
    public static int liczbaSingletonów2 = 0;
    public static int liczbaSingletonów3 = 0;
    public static UserService userService;
    private int liczbaSingletonów5 = 0;


    public UserService(UserRepository userRepository) {
        UserService.liczbaSingletonów++;
        UserService.liczbaSingletonów2++;
        this.userRepository = userRepository;
        this.liczbaSingletonów5++;
    }

    public UserService() {
        UserService.liczbaSingletonów++;
        UserService.liczbaSingletonów3++;

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.userRepository = context.getBean("getRepository", UserRepository.class);
    }


    public List<User> getUsers() {
        System.out.println(UserService.liczbaSingletonów);
        System.out.println(UserService.liczbaSingletonów2);
        System.out.println(UserService.liczbaSingletonów3);
        System.out.println(this.liczbaSingletonów5);
        return userRepository.getUsers();
    }

    public User addUser(User user) {
        this.userRepository.addUser(user);
        return user;
    }

}
