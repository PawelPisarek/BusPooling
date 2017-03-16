package BusPooling.config;

import BusPooling.rest.domain.Person;
import BusPooling.rest.infrastructure.DBAL.Person.IDbalPersonQuery;
import BusPooling.rest.infrastructure.entity.PersonEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by pawe on 3/16/17.
 */


public class AuthenticationFacade implements IAuthenticationFacade {

    IDbalPersonQuery iDbalPersonQuery;

    public AuthenticationFacade(IDbalPersonQuery iDbalPersonQuery) {
        this.iDbalPersonQuery = iDbalPersonQuery;
    }

    @Override
    public PersonEntity getAuthentication() {
        return iDbalPersonQuery.findByEmailEntity(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}