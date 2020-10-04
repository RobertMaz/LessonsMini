package com.innopolis.controllers;


import com.innopolis.entity.Product;
import com.innopolis.repositories.specifications.ProductSpecification;
import com.innopolis.servises.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAll(Model model, @RequestParam(name = "p", defaultValue = "1") Integer pageNumber ,
                          @RequestParam(name = "minCost", required = false) Integer minCost){
        Specification<Product> spec = Specification.where(null);
        if (minCost != null){
            spec = spec.and(ProductSpecification.costGEThan(minCost));
        }
        List<Product> content = productService.findAll(spec, pageNumber).getContent();
        model.addAttribute("productList", content);
        return "products";
    }

    @GetMapping("/edit/{id}")
    public String editProd(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "editProd";
    }

    @GetMapping("/add")
    public String addProd(){
        return "addProd";
    }

    @PostMapping("/add")
    public String addedProd(@ModelAttribute Product product){
        productService.saveOrUpdateProduct(product);
        return "redirect:/products/";
    }

    @PostMapping("/edit")
    public String edited(@ModelAttribute Product product){
        productService.saveOrUpdateProduct(product);
        return "redirect:/products/";
    }

    @GetMapping("/JSON/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable Long id){
//        return productService.findProductById(id).orElseThrow(()-> new RuntimeException("not find Product by id" + id));
        return productService.findById(id);
    }
}
