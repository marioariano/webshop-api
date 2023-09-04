package com.webshop.domain.dto;

import lombok.Data;

/**
 * Class PutDTO of Product
 */
@Data
public class ProductPutDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
}
