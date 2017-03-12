package BusPooling.rest.infrastructure;

import BusPooling.rest.aplication.query.DelayedTransportView.DelayedTransportView;
import BusPooling.rest.aplication.query.MyOfferView.MyOfferView;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.repository.IRepository;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by pawe on 3/1/17.
 */
public class DbalMyOfferQuery {

    private final IRepository<MyOffer, MyOfferEntity> repository;

    public DbalMyOfferQuery(IRepository<MyOffer, MyOfferEntity> repository) {
        this.repository = repository;
    }

    public List<MyOfferView> getAll() {
        return this.repository.getAll().stream()
                .map(user -> new MyOfferView(user.getId(), user.getPrice(), user.getTimeToLeft(), user.getAuthor()))
                .collect(Collectors.toList());
    }

    public MyOfferEntity getById(String id) {
        final MyOfferEntity byId = this.repository.findById(id);
        return byId;
    }

}
