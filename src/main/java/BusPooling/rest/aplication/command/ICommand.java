package BusPooling.rest.aplication.command;

import BusPooling.AppConfiguration;

/**
 * Created by pawe on 2/27/17.
 */
public interface ICommand {
    AppConfiguration.Commands getKey();
}
