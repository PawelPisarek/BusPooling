package BusPooling.rest.infrastructure;

import BusPooling.rest.aplication.query.DelayedTransportView.DelayedTransportView;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.repository.IRepository;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by pawe on 3/1/17.
 */
public class DbalDelayedTransportQuery {

    private final IRepository<DelayedTransport,DelayedTransportEntity> repository;

    public DbalDelayedTransportQuery(IRepository<DelayedTransport,DelayedTransportEntity> repository) {
        this.repository = repository;
    }

    public List<DelayedTransportView> getAll() {
        return this.repository.getAll().stream()
                .map(user -> new DelayedTransportView(user.getId(), user.getNameTrain(), user.getFrom(), user.getAlternative(), user.getLat(), user.getLng()))
                .collect(Collectors.toList());
    }

}
