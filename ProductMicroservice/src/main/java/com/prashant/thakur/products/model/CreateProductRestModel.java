package com.prashant.thakur.products.model;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateProductRestModel {
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
