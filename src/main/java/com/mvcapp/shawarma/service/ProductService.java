package com.mvcapp.shawarma.service;

import com.mvcapp.shawarma.model.entity.ProductEntity;
import com.mvcapp.shawarma.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> findAll(){
        return productRepository.findAll();
    }
    public Optional<ProductEntity> findById(Integer id){
        return productRepository.findById(id);
    }
    public List<ProductEntity> findByCategoryId(Integer categoryId){
        return productRepository.findByCategoryId(categoryId);
    }

}
