package com.example.demo.web.controller;

import com.example.demo.domain.Product;
import com.example.demo.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping()
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
//    public List<Product> getAll(){
//        return productService.getAll();
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId){
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }//aca podemos observar, que se hace uso de un map, porque el entity retorna es un tipo Optional, asi que es necesario acoger el producto
    //que retorna mapearlo y aplicarle el responseentity
//    public Optional<Product> getProduct(@PathVariable("id") int productId){
//        return productService.getProduct(productId);
//    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
//    public Optional<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
//        return productService.getByCategory(categoryId);
//    }
    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
//    public Product save(@RequestBody Product product){
//        return productService.save(product);
//    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId){
      if (productService.delete(productId)){
          return new ResponseEntity(HttpStatus.OK);
      }else
          return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
