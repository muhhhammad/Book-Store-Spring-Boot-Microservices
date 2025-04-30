package com.project.catalog.record.mapper;

import com.project.catalog.entity.Product;
import com.project.catalog.record.ProductRecord;

public class ProductMapper {

    public static ProductRecord toProduct(Product product){
         return new ProductRecord(
                 product.getCode(),
                 product.getName(),
                 product.getDescription(),
                 product.getImageUrl(),
                 product.getPrice()
         );
    }

}
