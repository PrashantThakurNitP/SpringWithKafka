package com.prashant.thakur.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor//needed for deserialization, to create instance of class before it can populate membervariable
public class ProductCreatedEvent {
    private String productId;

    private String title;
    private BigDecimal price;
    private Integer quantity;

}

