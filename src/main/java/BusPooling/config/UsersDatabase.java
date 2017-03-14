package BusPooling.config;



import BusPooling.config.data.BearerToken;
import BusPooling.config.data.CulinaryPreference;
import BusPooling.config.data.Interest;
import BusPooling.config.data.User;
import org.springframework.social.facebook.api.Facebook;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafal on 4/4/16.
 */
public interface UsersDatabase {

    List<User> getUsers();


    void updateUser(String id, User user);



    User getUser(String id);

    User findByLogin(String email);

    boolean checkIfUserExists(String email);

    boolean checkUserInterests(ArrayList interestNames);

    boolean checkGender(String gender);

    void removePreference(String id, String preferenceId);

    void removeInterest(String id, String interestId);


    void sendConfirmationEmail(String email);


    User getCurrentlyLoggedUser();
}