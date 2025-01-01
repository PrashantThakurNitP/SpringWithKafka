package com.prashant.thakur.products.controller;

import com.prashant.thakur.products.exception.ErrorMessage;
import com.prashant.thakur.products.model.CreateProductRestModel;
import com.prashant.thakur.products.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductControllerImpl implements ProductController{
    ProductService productService;
    public ProductControllerImpl(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductRestModel product){

        String productId = null;
        try {
            productId = productService.createProduct(product);
        } catch (Exception e) {
            log.error("Exception occurred {}",e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(new Date(), e.getMessage(),"/products"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
