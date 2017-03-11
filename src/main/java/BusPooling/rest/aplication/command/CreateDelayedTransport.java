package BusPooling.rest.aplication.command;

import BusPooling.AppConfiguration;
import BusPooling.rest.domain.DelayedTransport;

import static BusPooling.AppConfiguration.Commands.CREATE_DELAYED_TRANSPORT;


/**
 * Created by pawe on 3/4/17.
 */
public class CreateDelayedTransport implements ICommand {

   DelayedTransport delayedTransport;

    public DelayedTransport getDelayedTransport() {
        return delayedTransport;
    }

    public void setDelayedTransport(DelayedTransport delayedTransport) {
        this.delayedTransport = delayedTransport;
    }

    public CreateDelayedTransport(DelayedTransport delayedTransport) {

        this.delayedTransport = delayedTransport;
    }

    @Override
    public AppConfiguration.Commands getKey() {

        return CREATE_DELAYED_TRANSPORT;
    }
}
