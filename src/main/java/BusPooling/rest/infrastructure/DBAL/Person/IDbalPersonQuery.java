package BusPooling.rest.infrastructure.DBAL.Person;

import BusPooling.rest.aplication.query.Person.PersonView;
import BusPooling.rest.infrastructure.entity.PersonEntity;

/**
 * Created by pawe on 3/15/17.
 */
public interface IDbalPersonQuery {
 PersonView findByEmail(String email);
}
