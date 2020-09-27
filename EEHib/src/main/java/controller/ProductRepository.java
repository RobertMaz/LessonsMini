package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persist.Product;

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
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager en;

    @Resource
    private UserTransaction ut;

    @Inject
    private CategoryRepository categoryRepository;

    public ProductRepository() {
    }

    @Transactional
    public void insert(Product product){
        logger.info("Inserting new Product");
        en.persist(product);
    }

    @Transactional
    public void update(Product product){
        en.merge(product);
    }

    @Transactional
    public void delete(long id){
        Product product  = en.find(Product.class, id);
        if (product != null){
            en.remove(product);
        }
    }

    public Optional<Product> findById(long id){
        Product product = en.find(Product.class, id);
        if (product != null){
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public List<Product> findAll(){
        return en.createQuery("from Product", Product.class).getResultList();
    }


}
