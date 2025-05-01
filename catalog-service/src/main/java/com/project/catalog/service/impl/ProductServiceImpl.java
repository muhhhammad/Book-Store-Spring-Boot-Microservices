package com.project.catalog.service.impl;

import com.project.catalog.ApplicationProperties;
import com.project.catalog.entity.Product;
import com.project.catalog.record.PagedResult;
import com.project.catalog.record.ProductRecord;
import com.project.catalog.repository.ProductRepository;
import com.project.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.catalog.record.mapper.ProductMapper;

import java.util.Optional;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ApplicationProperties properties;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ApplicationProperties properties) {
        this.productRepository = productRepository;
        this.properties = properties;
    }

    @Override
    public PagedResult<ProductRecord> getProducts(int pageNo) {
        Sort sort = Sort.by("name").ascending();
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;
        Pageable pageable = PageRequest.of(pageNo, properties.pageSize(), sort);
        Page<ProductRecord> productPage =
                productRepository.findAll(pageable).map(ProductMapper::toProduct);

        return new PagedResult<>(
                productPage.getContent(),
                productPage.getTotalElements(),
                productPage.getNumber() + 1,
                productPage.getTotalPages(),
                productPage.isFirst(),
                productPage.isLast(),
                productPage.hasNext(),
                productPage.hasPrevious()
        );

    }

    @Override
    public Optional<ProductRecord> getProductByCode(String code) {
        return productRepository.findByCode(code).map(ProductMapper::toProduct);
    }
}
