package BusPooling.config.mongo;


import BusPooling.config.data.ConfirmationToken;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by rafal on 5/21/16.
 */
public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, String> {
    public ConfirmationToken findByToken(String token);
}
