package BusPooling.config.mongo;


import BusPooling.config.data.Interest;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by rafal on 5/23/16.
 */
public interface InterestRepository extends MongoRepository<Interest, String> {
    Interest findByInterestName(String name);
}
