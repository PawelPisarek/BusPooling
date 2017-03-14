package BusPooling.config;

import BusPooling.config.data.*;
import BusPooling.config.utils.*;
import BusPooling.config.Exceptions.*;
import BusPooling.config.mongo.ConfirmationTokenRepository;
import BusPooling.config.mongo.UserRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.Facebook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafal on 5/23/16.
 */
public class UsersMongoDatabase implements UsersDatabase {
    UserRepository userRepository;
    ConfirmationTokenRepository confirmationTokenRepository;
    //TODO change for enum?
    private String MALE = "mężczyzna";
    private String FEMALE = "kobieta";

    String SERVER_BASE_API_ADDRESS = "http://localhost:8080/api";
    String EMAIL_FROM = "tastymeeting@pydyniak.pl";
    //    String EMAIL_FROM = "tastymeeting123"; //gmail.com
    String EMAIL_PASSWORD = "Tasty123";
    String SMTP_HOST = "mail.pydyniak.pl";
    String SMTP_PORT = "587";
    String CONFIRMATION_EMAIL_SUBJECT = "Aktywacja konta w TastyMeeting";
    String CONFIRMATION_EMAIL_BODY = "Aby aktywować swoje konto wejdź na adres " + SERVER_BASE_API_ADDRESS + "/users/activate/";

    public UsersMongoDatabase( UserRepository userRepository) {
//        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }



    private boolean isUsernameValidEmail(String email) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return email.matches(regex);
    }

    @Override
    public void updateUser(String id, User user) {


        user.setId(id);
        userRepository.save(user);
    }


    @Override
    public User getUser(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByLogin(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean checkIfUserExists(String email) {
        return findByLogin(email) != null;
    }

    @Override
    public boolean checkUserInterests(ArrayList interestNames) {
        //TODO implement when needed
        return true;
    }

    @Override
    public boolean checkGender(String gender) {
        return MALE.compareToIgnoreCase(gender) == 0 || FEMALE.compareToIgnoreCase(gender) == 0
                || gender == null;
    }


    @Override
    public void removePreference(String id, String preferenceId) {
        User user = getUser(id);

    }

    @Override
    public void removeInterest(String id, String interestId) {

    }





    private String generateRandomPassword() {
        return SecureUidsGeneratorSingleton.getInstance().generateUID();
    }

    private BearerToken getBearerToken(User user) throws IOException {
        return OauthTokenHandlerSingleton.getInstance().getBearerTokenForUser(user);
    }

    @Override
    public void sendConfirmationEmail(String email) {
//        EmailHandler emailHandler = new EmailHandler();
//        String token = SecureUidsGeneratorSingleton.getInstance().generateUID();
//        User user = findByLogin(email);
//        ConfirmationToken confirmationToken = new ConfirmationToken(user.getId(), token);
//        confirmationTokenRepository.save(confirmationToken);
//
//        String body = CONFIRMATION_EMAIL_BODY + token;
//
//        emailHandler.sendFromSmtp(EMAIL_FROM, EMAIL_PASSWORD, email, CONFIRMATION_EMAIL_SUBJECT, body, SMTP_HOST, SMTP_PORT, false);
    }


    public void activateUser(String token) throws WrongIdException {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);

        if (confirmationToken == null)
            throw new WrongIdException();

        User user = getUser(confirmationToken.getUserId());
        user.setActive(true);
        updateUser(user.getId(), user);
    }

    @Override
    public User getCurrentlyLoggedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
