package com.project.catalog.record.mapper;

import com.project.catalog.entity.Product;
import com.project.catalog.record.ProductRecord;

public class ProductMapper {

    //This Will Map the Product(Entity) to ProductRecord So we can only reveal necessary Information
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
