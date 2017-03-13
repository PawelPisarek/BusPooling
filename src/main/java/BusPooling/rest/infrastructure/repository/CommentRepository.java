package BusPooling.rest.infrastructure.repository;


import BusPooling.rest.domain.Comment;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.infrastructure.entity.CommentEntity;
import BusPooling.rest.infrastructure.entity.MyOfferEntity;
import BusPooling.rest.infrastructure.entity.TransportOfferEntity;
import BusPooling.rest.repository.IRepository;
import BusPooling.rest.repository.MongoDatastore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by pawe on 3/3/17.
 */
public class CommentRepository extends AbstractRepository implements IRepository<Comment, CommentEntity>, MongoDatastore<Comment, CommentEntity, Comment> {


    private Collection<CommentEntity> listToSave;

    public CommentRepository(Datastore mongoDatabase) {
        super(mongoDatabase);
        this.listToSave = new ArrayList<CommentEntity>();
    }

    @Override
    public Collection<Comment> getAll() {
        return this.mongoDatabase.createQuery(CommentEntity.class).asList().stream().map(
                entity -> new Comment(entity.getId().toString(), entity.getUuid(), entity.getRoot(), entity.getText(), entity.getDelayedTransportEntity()))
                .collect(Collectors.toList());
    }

    @Override
    public Comment addData(Comment data) {
        final CommentEntity commentEntity = buildEntity(data);
        this.mongoDatabase.save(commentEntity);
        return data;
    }

    @Override
    public CommentEntity buildEntity(Comment object) {
        return new CommentEntity(object.getUuid(),object.getRoot(),object.getText(),object.getDelayedTransportEntity());
    }

    @Override
    public CommentEntity findById(String id) {
        Collection<CommentEntity> lists = new ArrayList<>();

        for (CommentEntity commentEntity : this.mongoDatabase.find(CommentEntity.class)) {
            lists.add(commentEntity);
        }

        final CommentEntity commentEntity = lists.stream().filter(entitty -> {
            return id.equals(buildResponse(entitty).getId());
        }).collect(Collectors.toList()).stream().findAny()
                .orElse(null);
        if (commentEntity == null) throw new NotFoundException();
        return commentEntity;
    }

    @Override
    public Comment buildResponse(CommentEntity entity) {
        return new Comment(entity.getId().toString(), entity.getUuid(), entity.getRoot(), entity.getText(), entity.getDelayedTransportEntity());
    }

    @Override
    public CommentEntity update(CommentEntity save) {
        return new CommentEntity(save.getUuid(), save.getRoot(), save.getText(), save.getDelayedTransportEntity());
    }

    @Override
    public void save() {
        for (CommentEntity commentEntity : listToSave) {
            mongoDatabase.save(commentEntity);
        }
    }
}
