package BusPooling.rest.infrastructure.DBAL.Person;

import BusPooling.rest.aplication.query.Person.PersonView;
import BusPooling.rest.infrastructure.entity.PersonEntity;
import org.mongodb.morphia.Datastore;

/**
 * Created by pawe on 3/15/17.
 */
public class DbalPersonQuery implements IDbalPersonQuery {
    Datastore mongoDatabase;


    public DbalPersonQuery(Datastore mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    @Override
    public PersonView findByEmail(String email) {
        final PersonEntity username = mongoDatabase.find(PersonEntity.class).
                filter("username", email).
                asList().
                stream().
                findFirst().
                get();
        return new PersonView(username.getUsername(),
                username.getPassword(),
                username.getName(),
                username.getSurname(),
                username.getBirthday(),
                username.getGender(),
                username.isActive(),
                username.getFacebookUID(),
                username.getGeoLat(),
                username.getGeoLng()
        );
    }
}
