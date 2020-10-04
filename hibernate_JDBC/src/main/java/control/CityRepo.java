package control;


import persist.City;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public class CityRepo implements Serializable {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;


    private UserTransaction ut;

    public CityRepo() {
    }

    @Transactional
    public void insert(City city){
        em.persist(city);
    }

    @Transactional
    public void update(City city){
        em.merge(city);
    }

    @Transactional
    public void delete(int id){
        City city = em.find(City.class, id);
        if (city != null){
            em.remove(city);
        }
    }

    public Optional<City> findById(int id){
        City city = em.find(City.class, id);
        if (city != null)   {
            return Optional.of(city);
        }
        return Optional.empty();
    }

    public List<City> findAll(){
        return em.createQuery("from City", City.class).getResultList();
    }

    public Optional<City> findByName(String name){
        City city = em.createQuery("from City c where c.name = :name", City.class)
                .setParameter("name", name)
                .getSingleResult();
        if (city != null){
            return Optional.of(city);
        }
        return Optional.empty();
    }
}
