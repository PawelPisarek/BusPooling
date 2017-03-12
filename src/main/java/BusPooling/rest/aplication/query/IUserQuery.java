package BusPooling.rest.aplication.query;

import BusPooling.rest.aplication.query.UserView.UserQuery;

import java.util.List;

/**
 * Created by pawe on 3/1/17.
 */
public interface IUserQuery {
    public List<UserQuery> getAll();
}
