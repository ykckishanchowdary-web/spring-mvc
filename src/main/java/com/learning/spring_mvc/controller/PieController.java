package com.learning.spring_mvc.controller;


import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PieController {

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

}
