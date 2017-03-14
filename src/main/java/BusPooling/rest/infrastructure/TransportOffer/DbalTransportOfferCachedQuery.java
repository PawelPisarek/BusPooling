package BusPooling.rest.infrastructure.TransportOffer;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.query.TransportView.TransportOfferView;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pawe on 3/14/17.
 */
public class DbalTransportOfferCachedQuery implements IDbalTransportOfferQuery {

    private IDbalTransportOfferQuery dbalTransportOfferQuery;

    public DbalTransportOfferCachedQuery(IDbalTransportOfferQuery dbalTransportOfferQuery) {
        this.dbalTransportOfferQuery = dbalTransportOfferQuery;
    }


    @Override
    public List<TransportOfferView> getAll(String delayedTransportId) {
        if (true) { //exist in cached
           return this.dbalTransportOfferQuery.getAll(delayedTransportId);
        }
        return new ArrayList<>();
    }

    @Override
    public TransportOfferEntity getById(String id) {
        return null;
    }
}
