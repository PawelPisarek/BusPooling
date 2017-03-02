package BusPooling.rest.aplication.query.User;

import BusPooling.rest.aplication.query.User.UserView.UserQuery;

import java.util.List;

/**
 * Created by pawe on 3/1/17.
 */
public interface IUserQuery {
    List<UserQuery> getAll();
}
