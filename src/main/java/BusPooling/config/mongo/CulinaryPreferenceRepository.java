package BusPooling.config.mongo;


import BusPooling.config.data.CulinaryPreference;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by rafal on 5/23/16.
 */
public interface CulinaryPreferenceRepository extends MongoRepository<CulinaryPreference, String> {
    CulinaryPreference findByCulinaryPreferenceName(String name);
}
