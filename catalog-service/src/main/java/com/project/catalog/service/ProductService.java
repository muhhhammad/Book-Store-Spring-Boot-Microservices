package com.project.catalog.service;

import com.project.catalog.record.PagedResult;
import com.project.catalog.record.ProductRecord;

public interface ProductService {

    public PagedResult<ProductRecord> getProducts(int pageNo);

}
