package com.techno.basicspringboot.service;

import com.techno.basicspringboot.dto.BaseResponseDto;
import com.techno.basicspringboot.dto.request.ProductRequestDto;
import com.techno.basicspringboot.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    BaseResponseDto getOneProduct(Long id);
    BaseResponseDto getAllProducts();
    BaseResponseDto saveProduct(ProductRequestDto productRequestDto);
    BaseResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);
    BaseResponseDto deleteProduct(Long id);
}
