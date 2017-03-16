package BusPooling.rest.service;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.Person.RegisterPerson;
import BusPooling.rest.aplication.command.Person.UpdatePerson;
import BusPooling.rest.domain.Person;
import BusPooling.rest.infrastructure.DAO.PersonDAO;
import BusPooling.rest.infrastructure.DAO.RegisterPersonDAO;
import BusPooling.rest.infrastructure.UnitOfWork;
import BusPooling.rest.infrastructure.entity.PersonEntity;
import BusPooling.rest.repository.IRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {
    @Test
    public void getAll() throws Exception {


    }

    @Test
    public void addFromHandle() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        final IRepository personEntityIRepository = context.getBean("personEntityIRepository", IRepository.class);

        final PersonService personService = new PersonService(personEntityIRepository);
        final RegisterPerson command = new RegisterPerson(new RegisterPersonDAO("ktos", "hasło"));
        personService.addFromHandle(command);

    }

    @Before
    public void setUp() {
        personService = new PersonService(repository);
    }

    @Mock
    IRepository repository;
    PersonService personService;

    @Test
    public void addFromHandle_unit() throws Exception {
        final RegisterPerson mock = mock(RegisterPerson.class);
        final Person value = new Person();
        when(mock.getRegisteringPerson()).thenReturn(value);
        personService.addFromHandle(mock);
        verify(repository, Mockito.times(1)).addData(value);
        verify(mock, Mockito.times(1)).getRegisteringPerson();
    }


    @Test
    public void update() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        final IRepository<Person, PersonEntity> personEntityIRepository = context.getBean("personEntityIRepository", IRepository.class);

        final PersonService personService = new PersonService(personEntityIRepository);
        final PersonDAO transportOffer = new PersonDAO("dowolny", "dowolne", "nowehasło", "imie", "nazwisko", new Date(), "asd", false, "sad", "sad", "asd", 1000);
        final PersonEntity byId = personEntityIRepository.findById("58c988f0e154885432476109");
        final UpdatePerson command = new UpdatePerson(transportOffer, byId);
        HashMap<AppConfiguration.Repositories, IRepository> hashMap = context.getBean("getRepositories", HashMap.class);

        final UnitOfWork unitOfWork = new UnitOfWork(hashMap);
        unitOfWork.registerRepository(AppConfiguration.Repositories.PERSON_REPOSITORY);
        personService.update(command);
        unitOfWork.commit();

    }

}