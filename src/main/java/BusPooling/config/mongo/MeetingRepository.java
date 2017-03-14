package BusPooling.config.mongo;


import BusPooling.config.data.Meeting;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by rafal on 5/23/16.
 */
public interface MeetingRepository extends MongoRepository<Meeting, String> {
    Meeting findByTitle(String title);
}
