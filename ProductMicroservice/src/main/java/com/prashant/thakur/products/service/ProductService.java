package com.prashant.thakur.products.service;

import com.prashant.thakur.products.model.CreateProductRestModel;

import java.util.concurrent.ExecutionException;

public interface ProductService {

   String createProduct(CreateProductRestModel createProductRestModel) throws ExecutionException, InterruptedException;
}
