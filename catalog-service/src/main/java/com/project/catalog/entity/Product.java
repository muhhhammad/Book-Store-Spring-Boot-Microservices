package com.project.catalog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    //Use @Lombok Annotation if you want to.

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name =  "product_id_generator", sequenceName = "product_id_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Product Code is Required")
    private String code;

    @Column(nullable = false)
    @NotEmpty(message = "Product Name is Required")
    private String name;

    private String description;

    private String imageUrl;

    @Column(nullable = false)
    @NotEmpty(message = "Product Price is Required")
    @DecimalMin("0.1")
    private BigDecimal price;

    public Product(){

    }

    public Product(String code, String name, String description, String imageUrl, BigDecimal price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Product(Long id, String code, String name, String description, String imageUrl, BigDecimal price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
