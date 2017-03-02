package BusPooling.rest.infrastructure;

import BusPooling.rest.infrastructure.entity.UserEntityMongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

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


    static public Datastore mongoClient() {
        if (datastore == null) {
            Morphia morphia = new Morphia();
            morphia.map(UserEntityMongo.class);
            MongoClient client = createMongoClient();
            datastore = morphia.createDatastore(client, DATABASE);
        }

        return datastore;
    }
    private static MongoClient createMongoClient() {
        MongoCredential credential = MongoCredential.createMongoCRCredential(
                USER_NAME,
                DATABASE,
                PASSWORD.toCharArray()
        );
        List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
        credentialsList.add(credential);

        try {
            ServerAddress serverAddress = new ServerAddress(HOST, PORT);
            return new MongoClient(serverAddress, credentialsList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    public void AddUser() {

        MongoEntityManager testmorphia = new MongoEntityManager(MongoEntityManagerTest.mongoClient());

        testmorphia.AddNewUser();


    }
}
