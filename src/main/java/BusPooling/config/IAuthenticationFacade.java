package BusPooling.config;

import BusPooling.rest.domain.Person;
import BusPooling.rest.infrastructure.entity.PersonEntity;
import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    PersonEntity getAuthentication();
}