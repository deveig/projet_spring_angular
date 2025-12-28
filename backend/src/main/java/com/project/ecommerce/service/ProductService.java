package com.project.ecommerce.service;

import com.project.ecommerce.dto.ProductDTO;
import com.project.ecommerce.entity.Product;
import com.project.ecommerce.exception.ResourceNotFoundException;
import com.project.ecommerce.mapper.ProductMapper;
import com.project.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Transactional(readOnly = true)
    public List<ProductDTO> getAll(){
        return productRepository.findAll().stream().map((u) -> productMapper.toDto(u)).toList();
    }

    @Transactional(readOnly = true)
    public ProductDTO getById(Long id){
        return productMapper.toDto(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id)));
    }

    public ProductDTO saveProduct(ProductDTO productDTO){
        return productMapper.toDto(productRepository.save(productMapper.toEntity(productDTO)));
    }

    public ProductDTO update(Long id, ProductDTO productDTO){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return productMapper.toDto(productRepository.save(productMapper.updateEntity(productDTO, product)));
    }

    public void delete(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getByCriteria(String criteria){
        return productRepository.findByCriteria(criteria).stream().map((p) -> productMapper.toDto(p)).toList();
    }
}
