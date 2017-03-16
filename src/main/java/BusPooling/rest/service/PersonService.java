package BusPooling.rest.service;


import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.Person.RegisterPerson;
import BusPooling.rest.aplication.command.Person.UpdatePerson;
import BusPooling.rest.domain.Person;
import BusPooling.rest.infrastructure.entity.PersonEntity;
import BusPooling.rest.repository.IRepository;
import BusPooling.rest.service.IService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonService implements IService<RegisterPerson, UpdatePerson, Person> {

    private final IRepository<Person, PersonEntity> iRepository;

    public PersonService(IRepository<Person, PersonEntity> iRepository) {
        this.iRepository = iRepository;
    }

    public PersonService() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.iRepository = context.getBean("personEntityIRepository", IRepository.class);
    }

    @Override
    public Collection<Person> getAll() {
        return this.iRepository.getAll();
    }

    @Override
    public void addFromHandle(RegisterPerson command) {
        iRepository.addData(command.getRegisteringPerson());
    }

    @Override
    public void update(UpdatePerson command) {
        final PersonEntity personEntity = command.getPersonEntity();
        personEntity.setName(command.getPersonDAO().getName());
        personEntity.setActive(personEntity.isActive());
        personEntity.setBirthday(command.getPersonDAO().getBirthday());
        personEntity.setBirthday(command.getPersonDAO().getBirthday());
        personEntity.setGender(command.getPersonDAO().getGender());
        personEntity.setGeoLat(command.getPersonDAO().getGeoLat());
        personEntity.setGeoLng(command.getPersonDAO().getGeoLng());
        personEntity.setUsername(command.getPersonDAO().getUsername());
        this.iRepository.update(personEntity);
    }
}
