package all.services;

import all.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import all.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Don't find product with id=" + id));
    }

    public List<Product> getAll(Specification<Product> specification, int page){
        if (page < 1){
            page = 1;
        }
        return productRepository.findAll(specification, PageRequest.of(page - 1, 5)).getContent();
    }

    public void addProd(Product product){
        productRepository.save(product);
    }
}
