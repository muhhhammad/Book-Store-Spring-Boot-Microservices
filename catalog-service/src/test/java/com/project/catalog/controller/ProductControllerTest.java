package com.project.catalog.controller;

import com.project.catalog.AbstractIntegrationTest;
import com.project.catalog.entity.Product;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

//@Disabled
@Sql("/test-data.sql") //Takes the data From this File to check
class ProductControllerTest extends AbstractIntegrationTest {

    //Checks if API is Working Fine
    @Test
    void shouldReturnProducts(){

        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElement", is(15))
                .body("pageNumber", is(1))
                .body("totalPages", is(2))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));

    }

    @Test
    void shouldGetProductByCode(){
        Product product = given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", "P100")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(Product.class);

        assertThat(product.getCode()).isEqualTo("P100");
        assertThat(product.getName()).isEqualTo("1984");
        assertThat(product.getDescription()).isEqualTo("A dystopian future where Big Brother watches everything.");
        assertThat(product.getPrice()).isEqualTo(new BigDecimal("14.99"));
    }

    @Test
    void shouldReturnNotFoundWhenProductCodeNotExist(){
        String code = "invalid_product_code";
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", code)
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("title", is("Product Not Found"))
                .body("detail", equalTo("Product with Code: "+ code +" Not Found"))
        ;
    }

}