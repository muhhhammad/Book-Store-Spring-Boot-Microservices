package com.project.catalog.service;

import com.project.catalog.entity.Product;
import com.project.catalog.record.PagedResult;
import com.project.catalog.record.ProductRecord;

import java.util.Optional;

public interface ProductService {

    public PagedResult<ProductRecord> getProducts(int pageNo);

    public Optional<ProductRecord> getProductByCode(String code);

}
