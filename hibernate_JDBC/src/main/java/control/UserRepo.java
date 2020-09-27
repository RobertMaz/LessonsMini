package control;


import persist.User;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named
public class UserRepo {

    @PersistenceContext
    private EntityManager en;

    @Resource
    private UserTransaction ut;

    @Inject
    private CityRepo cityRepo;


    @Transactional
    public void insert(User user){
        en.persist(user);
    }

    @Transactional
    public void update(User user){
        en.merge(user);
    }

    @Transactional
    public void delete(int id){
        User user = en.find(User.class, id);
        if (user != null){
            en.remove(user);
        }
    }

    public Optional<User> findById(int id){
        User user = en.find(User.class, id);
        if (user != null){
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public List<User> findAll(){
        return en.createQuery("from User", User.class).getResultList();
    }
}
