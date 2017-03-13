package BusPooling.rest.service;


import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.command.Comment.CreateComment;
import BusPooling.rest.domain.Comment;
import BusPooling.rest.infrastructure.DAO.CommentDAO;
import BusPooling.rest.infrastructure.entity.CommentEntity;
import BusPooling.rest.repository.IRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CommentService implements IService<CreateComment, Object, Comment> {

    private final IRepository<Comment, CommentEntity> iRepository;

    public CommentService(IRepository<Comment, CommentEntity> iRepository) {
        this.iRepository = iRepository;
    }

    public CommentService() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.iRepository = context.getBean("getCommentRepository", IRepository.class);
    }

    @Override
    public Collection<Comment> getAll() {
        return this.iRepository.getAll();
    }

    @Override
    public void addFromHandle(CreateComment command) {
        iRepository.addData(command.getComment());
    }

    @Override
    public void update(Object command) {

    }
}
