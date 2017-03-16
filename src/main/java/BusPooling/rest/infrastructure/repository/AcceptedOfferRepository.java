package BusPooling.rest.infrastructure.repository;


import BusPooling.rest.domain.AcceptedOffer;
import BusPooling.rest.domain.Comment;
import BusPooling.rest.infrastructure.entity.AcceptedOfferEntity;
import BusPooling.rest.infrastructure.entity.CommentEntity;
import BusPooling.rest.repository.IRepository;
import BusPooling.rest.repository.MongoDatastore;
import org.mongodb.morphia.Datastore;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by pawe on 3/3/17.
 */
public class AcceptedOfferRepository extends AbstractRepository implements IRepository<AcceptedOffer, AcceptedOfferEntity>, MongoDatastore<AcceptedOffer, AcceptedOfferEntity, AcceptedOffer> {


    private Collection<AcceptedOfferEntity> listToSave;

    public AcceptedOfferRepository(Datastore mongoDatabase) {
        super(mongoDatabase);
        this.listToSave = new ArrayList<AcceptedOfferEntity>();
    }

    @Override
    public Collection<AcceptedOffer> getAll() {
        return null;
    }

    @Override
    public AcceptedOffer addData(AcceptedOffer data) {
        final AcceptedOfferEntity commentEntity = buildEntity(data);
        this.mongoDatabase.save(commentEntity);
        return data;
    }

    @Override
    public AcceptedOfferEntity buildEntity(AcceptedOffer object) {
        return new AcceptedOfferEntity(object.getPersonEntity(), object.getTransportOffer());
    }

    @Override
    public AcceptedOfferEntity findById(String id) {
        return null;
    }

    @Override
    public AcceptedOffer buildResponse(AcceptedOfferEntity entity) {
        return new AcceptedOffer(entity.getId().toString(), entity.getPersonEntity(), entity.getTransportOffer());
    }

    @Override
    public AcceptedOfferEntity update(AcceptedOfferEntity save) {
        return null;
    }

    @Override
    public void save() {
        for (AcceptedOfferEntity acceptedOfferEntity : listToSave) {
            this.mongoDatabase.save(acceptedOfferEntity);
        }
    }
}
