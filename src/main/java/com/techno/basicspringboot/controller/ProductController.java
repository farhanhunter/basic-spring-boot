package com.techno.basicspringboot.controller;

import com.techno.basicspringboot.dto.BaseResponseDto;
import com.techno.basicspringboot.dto.request.ProductRequestDto;
import com.techno.basicspringboot.entity.Product;
import com.techno.basicspringboot.exception.ProductNotFoundException;
import com.techno.basicspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDto> getOneProduct(@PathVariable Long id) {
        BaseResponseDto responseDto = productService.getOneProduct(id);
        return new ResponseEntity<>(responseDto, responseDto.getStatus());
    }

    @GetMapping
    public ResponseEntity<BaseResponseDto> getAllProducts() {
        BaseResponseDto responseDto = productService.getAllProducts();
        return new ResponseEntity<>(responseDto, responseDto.getStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponseDto> saveProduct(@RequestBody ProductRequestDto productRequestDto) {
        BaseResponseDto responseDto = productService.saveProduct(productRequestDto);
        return new ResponseEntity<>(responseDto, responseDto.getStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        BaseResponseDto responseDto = productService.updateProduct(id, productRequestDto);
        return new ResponseEntity<>(responseDto, responseDto.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDto> deleteProduct(@PathVariable Long id) {
        BaseResponseDto responseDto = productService.deleteProduct(id);
        return new ResponseEntity<>(responseDto, responseDto.getStatus());
    }
}
