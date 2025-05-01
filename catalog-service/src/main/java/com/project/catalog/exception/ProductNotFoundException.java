package com.project.catalog.exception;


import com.project.catalog.record.mapper.ProductMapper;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message){
        super(message);
    }

    public static ProductNotFoundException forCode(String code){
        return new ProductNotFoundException("Product with Code: "+ code +" Not Found");
    }

}
