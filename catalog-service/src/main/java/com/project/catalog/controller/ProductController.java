package com.project.catalog.controller;

import com.project.catalog.exception.ProductNotFoundException;
import com.project.catalog.record.PagedResult;
import com.project.catalog.record.ProductRecord;
import com.project.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
class ProductController {

    private final ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    PagedResult<ProductRecord> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo){
        return productService.getProducts(pageNo);//returns all the product in pageable form
    }

    //We will get product by code.
    @GetMapping("/{code}")
    ResponseEntity<ProductRecord> getProductByCode(@PathVariable String code){
        return productService.getProductByCode(code)
                .map(ResponseEntity::ok).orElseThrow(
                        () -> ProductNotFoundException.forCode(code)//If code is not correct this will throw Exception
                );

    }

}
