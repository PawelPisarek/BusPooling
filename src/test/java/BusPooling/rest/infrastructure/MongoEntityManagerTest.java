package BusPooling.rest.infrastructure;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;

import java.net.UnknownHostException;

/**
 * Created by pawe on 3/2/17.
 */
public class MongoEntityManagerTest {

    private static final String HOST = "ds113650.mlab.com";
    private static final int PORT = 13650;
    private static final String DATABASE = "testmorphia";
    private static final String USER_NAME = "tasty";
    private static final String PASSWORD = "tasty123";
    private static Datastore datastore;

   static private final String MONGO_URI = "mongodb://tasty:tasty123@ds113650.mlab.com:13650/testmorphia";

    static public Datastore mongoClient() {
        MongoClientURI mongoClientURI = new MongoClientURI(MONGO_URI);
        MongoClient mongoClient;
        try {
            mongoClient = new MongoClient(mongoClientURI);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        // http://stackoverflow.com/questions/6832517/how-to-check-from-a-driver-if-mongodb-server-is-running
        mongoClient.listDatabaseNames();

        Morphia morphia = new Morphia();

        new ValidationExtension(morphia);

         datastore = morphia.createDatastore(mongoClient,
                mongoClientURI.getDatabase());
         return datastore;
    }




    @Test
    public void AddUser() {

        MongoEntityManager testmorphia = new MongoEntityManager(MongoEntityManagerTest.mongoClient());

        testmorphia.AddNewUser();


    }
}
