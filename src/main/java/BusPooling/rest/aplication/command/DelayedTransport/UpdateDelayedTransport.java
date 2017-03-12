package BusPooling.rest.aplication.command.DelayedTransport;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;

import static BusPooling.AppConfiguration.Commands.CREATE_DELAYED_TRANSPORT;
import static BusPooling.AppConfiguration.Commands.UPDATE_DELAYED_TRANSPORT;


/**
 * Created by pawe on 3/4/17.
 */
public class UpdateDelayedTransport implements ICommand {

    DelayedTransport delayedTransport;
    DelayedTransportEntity delayedTransportEntity;

    public DelayedTransport getDelayedTransport() {
        return delayedTransport;
    }

    public DelayedTransportEntity getDelayedTransportEntity() {
        return delayedTransportEntity;
    }

    public UpdateDelayedTransport(DelayedTransport delayedTransport, DelayedTransportEntity delayedTransportEntity) {

        this.delayedTransport = delayedTransport;
        this.delayedTransportEntity = delayedTransportEntity;
    }


    @Override
    public AppConfiguration.Commands getKey() {

        return UPDATE_DELAYED_TRANSPORT;
    }
}
