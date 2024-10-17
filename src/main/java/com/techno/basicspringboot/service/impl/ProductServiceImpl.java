package com.techno.basicspringboot.service.impl;

import com.techno.basicspringboot.dto.BaseResponseDto;
import com.techno.basicspringboot.dto.request.ProductRequestDto;
import com.techno.basicspringboot.dto.response.ProductResponseDto;
import com.techno.basicspringboot.entity.Product;
import com.techno.basicspringboot.exception.CustomException;
import com.techno.basicspringboot.exception.ProductNotFoundException;
import com.techno.basicspringboot.repository.ProductRepository;
import com.techno.basicspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public BaseResponseDto getOneProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        Map<String, Object> data = new HashMap<>();
        data.put("product", convertEntityToDto(product));
        return BaseResponseDto.builder()
                .status(HttpStatus.OK)
                .description("Product found")
                .data(data)
                .build();
    }

    @Override
    public BaseResponseDto getAllProducts() {
        List<ProductResponseDto> products = productRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
        Map<String, Object> data = new HashMap<>();
        data.put("products", products);
        return BaseResponseDto.builder()
                .status(HttpStatus.OK)
                .description("All product display")
                .data(data)
                .build();
    }

    @Override
    public BaseResponseDto saveProduct(ProductRequestDto productRequestDto) {
        try {
            Product product = convertDtoToEntity(productRequestDto);
            Product savedProduct = productRepository.save(product);
            Map<String, Object> data = new HashMap<>();
            data.put("product", convertEntityToDto(savedProduct));
            return BaseResponseDto.builder()
                    .status(HttpStatus.OK)
                    .description("Product Saved Succes")
                    .data(data)
                    .build();
        } catch (Exception e) {
            throw new CustomException("Error saving product: " + e.getMessage());
        }
    }

    @Override
    public BaseResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = convertDtoToEntity(productRequestDto);
        product.setId(id);
        return saveProduct(productRequestDto);
    }

    @Override
    public BaseResponseDto deleteProduct(Long id) {
        try {
            if (!productRepository.existsById(id)) {
                throw new ProductNotFoundException("Product with ID " + id + " not found");
            }
            productRepository.deleteById(id);

            Map<String, Object> data = new HashMap<>();
            data.put("deletedProductId", id);
            return BaseResponseDto.builder()
                    .status(HttpStatus.OK)
                    .description("Product Deleted")
                    .data(data)
                    .build();
        } catch (Exception e) {
           throw new CustomException("Error deleting product: " + e.getMessage());
        }
    }

    private Product convertDtoToEntity(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        return product;
    }

    private ProductResponseDto convertEntityToDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}
