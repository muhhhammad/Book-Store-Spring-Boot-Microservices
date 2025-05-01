package com.project.catalog.repository;

import com.project.catalog.TestcontainersConfiguration;
import com.project.catalog.entity.Product;
import com.project.catalog.exception.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

// This is Called Slice Test Annotation
@DataJpaTest(
        properties = {
                "spring.test.database.replace=none", // with this property we are basically saying that we do not want in memory DB for the test.
                "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db",
        })
//This Means that we want postgresContainer for this class.
//Since we only have PostgresContainer this approach is fine, sometimes we have RabbitMQ container as well Then
//Then the upper approach is fine.
//@Import(TestcontainersConfiguration.class) //Either you use this Approach or Manually Type jdbc testContainer(tc) url
@Sql("/test-data.sql")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    //This test is not necessary since this method is From Jpa itself it is well tested and will work perfectly fine
    //This is just a demonstration of how we write tests...
    @Test
    void shouldGetAllProducts(){
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(15);
    }

    //This is our method so this one is needed to be checked since it is not from Jpa itself
    @Test
    void shouldGetProductByCode(){
        Product product = productRepository.findByCode("P100").orElseThrow();
        assertThat(product.getCode()).isEqualTo("P100");
        assertThat(product.getName()).isEqualTo("1984");
        assertThat(product.getDescription()).isEqualTo("A dystopian future where Big Brother watches everything.");
        assertThat(product.getPrice()).isEqualTo(new BigDecimal("14.99"));
    }

    //Assuming that the code is invalid or Non-existing then trying to find it then accepting it is empty
    @Test
    void shouldReturnEmptyWhenProductCodeNotExist(){
        assertThat(productRepository.findByCode("invalid_product_code")).isEmpty();
    }

}