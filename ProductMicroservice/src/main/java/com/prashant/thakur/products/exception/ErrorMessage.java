package com.prashant.thakur.products.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
public class ErrorMessage {
    private Date timeStamp;
    private String message;
    private String details;
}
