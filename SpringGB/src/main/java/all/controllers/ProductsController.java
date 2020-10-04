package all.controllers;

import all.entities.Product;
import all.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import all.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{page}")
    public String showAll(Model model,
                          @RequestParam(name = "minCost", required = false) Integer cost,
                          @PathVariable(value = "page") int page){
        Specification<Product> specification = Specification.where(null);

        if (cost != null){
            specification = specification.and(ProductSpecification.firstSpec(cost));
        }
        model.addAttribute("products", productService.getAll(specification, page));
        return "showAll";
    }

    @GetMapping
    public String all(){
        return "redirect:/products/1";
    }

    @PostMapping("/add")
    public String addProd(@ModelAttribute Product product){
        productService.addProd(product);
        return "redirect:/products/1";
    }

    @GetMapping("/add")
    public String add(){
        return "addProd";
    }

}
