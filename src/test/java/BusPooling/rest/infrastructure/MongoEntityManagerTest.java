package BusPooling.rest.infrastructure;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.junit.Test;

/**
 * Created by pawe on 3/2/17.
 */
public class MongoEntityManagerTest {

    private final String MONGO_URI = "mongodb://tasty:tasty123@ds113650.mlab.com:13650/testmorphia";
    @Test
    public void create_user()
    {
        new MongoEntityManager(new MongoClient(new MongoClientURI(MONGO_URI)).getDatabase("testmorphia")).AddNewUser();
    }
}
