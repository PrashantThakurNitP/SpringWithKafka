package com.prashant.thakur.products.controller;

import com.prashant.thakur.products.model.CreateProductRestModel;
import org.springframework.http.ResponseEntity;

public interface ProductController {
    ResponseEntity<Object> createProduct(CreateProductRestModel product);
}
