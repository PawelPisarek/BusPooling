package BusPooling.rest.infrastructure;

import BusPooling.rest.aplication.query.TransportView.TransportOfferView;

import java.util.List;

/**
 * Created by pawe on 3/13/17.
 */
public interface IDbal<V, E> {
    List<V> getAll(String delayedTransportId);

    E getById(String id);
}
