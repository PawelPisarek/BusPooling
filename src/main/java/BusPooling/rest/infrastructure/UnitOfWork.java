package BusPooling.rest.infrastructure;

import BusPooling.AppConfiguration;
import BusPooling.rest.repository.IRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pawe on 3/11/17.
 */
public class UnitOfWork {
    private ArrayList<IRepository> observerList;
    private HashMap<AppConfiguration.Repositories, IRepository> repositories;


    public UnitOfWork(HashMap<AppConfiguration.Repositories, IRepository> repositories) {
        this.repositories = repositories;
        this.observerList = new ArrayList<>();
    }

    public IRepository getRepository(AppConfiguration.Repositories repositories) {
        final IRepository repository = this.repositories.get(repositories);
        return repository;
    }

    public void registerRepository(AppConfiguration.Repositories repositories) {
        observerList.add(this.repositories.get(repositories));
    }

    public void commit() {
        for (IRepository o : observerList) {
            o.save();
        }
    }
}
