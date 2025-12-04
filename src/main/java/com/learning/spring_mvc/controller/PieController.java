package com.learning.spring_mvc.controller;


import com.learning.spring_mvc.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PieController {

    private List<Product> pies = new ArrayList<>(List.of(
            new Product(1,"Apple"),
            new Product(2,"cherry")
    ));

    @GetMapping("products/{id}")
    public Map<String,Object> getProduct(@PathVariable int id){
        return Map.of("id",id,"name","Product " + id);
    }

    @GetMapping("search")
    public Map<String,String> search(@RequestParam String keyword){
        return Map.of("result","searching for: "+ keyword);
    }

    @GetMapping("/products/{id}/reviews")
    public Map<String,String> getReviews(@PathVariable int id,
                                         @RequestParam(required = false) String sort){

        String sortInfo = (sort!=null)? "Sorted by " + sort : "Default sort";
        return Map.of("productId",String.valueOf(id),"sort",sortInfo);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product newProduct){
        System.out.println(newProduct);
        return newProduct;
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product updatedProduct){
        Product existingProduct = pies.stream()
                .filter(p -> p.getId()==id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Pie not found"));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setId(updatedProduct.getId());
        return existingProduct;
    };

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id){
        Product existingProduct = pies.stream()
                .filter(p -> p.getId()==id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Pie not found"));
        pies.remove(existingProduct);
        return ResponseEntity.noContent().build();
    };



}
