package com.techno.basicspringboot.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String name;
    private double price;
    private int quantity;
}
