package BusPooling.rest.infrastructure.repository;


import BusPooling.rest.domain.Comment;
import BusPooling.rest.domain.Person;
import BusPooling.rest.infrastructure.entity.CommentEntity;
import BusPooling.rest.infrastructure.entity.PersonEntity;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;
import BusPooling.rest.repository.IRepository;
import BusPooling.rest.repository.MongoDatastore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by pawe on 3/3/17.
 */
public class PersonRepository extends AbstractRepository implements IRepository<Person, PersonEntity>, MongoDatastore<Person, PersonEntity, Person> {


    private Collection<PersonEntity> listToSave;

    public PersonRepository(Datastore mongoDatabase) {
        super(mongoDatabase);
        this.listToSave = new ArrayList<PersonEntity>();
    }


    @Override
    public Collection<Person> getAll() {
        return mongoDatabase.find(PersonEntity.class)
                .asList()
                .stream()
                .map(entity -> buildResponse(entity))
                .collect(Collectors.toList());
    }

    @Override
    public Person addData(Person data) {
        PersonEntity userEntity = buildEntity(data);
        Key<PersonEntity> userEntityKey = this.mongoDatabase
                .save(userEntity);
        return data;
    }

    @Override
    public PersonEntity buildEntity(Person entity) {
        return new PersonEntity(entity.getUsername(),entity.getPassword(),entity.getName(),entity.getSurname(),entity.getBirthday(),entity.getGender(),entity.isActive(),entity.getFacebookUID(),entity.getGeoLat(),entity.getGeoLng());
    }

    @Override
    public PersonEntity findById(String id) {
        return null;
    }

    @Override
    public Person buildResponse(PersonEntity entity) {
        return new Person(entity.getId().toString(),entity.getUsername(),entity.getPassword(),entity.getName(),entity.getSurname(),entity.getBirthday(),entity.getGender(),entity.isActive(),entity.getFacebookUID(),entity.getGeoLat(),entity.getGeoLng());
    }

    @Override
    public PersonEntity update(PersonEntity save) {
        return null;
    }

    @Override
    public void save() {

    }
}
