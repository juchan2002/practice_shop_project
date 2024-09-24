package org.example.firstshopproject.service;

import lombok.RequiredArgsConstructor;
import org.example.firstshopproject.domain.Product;
import org.example.firstshopproject.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Optional<Product> getProductsById(int product_id){
        return productRepository.getProductsById(product_id);
    }

//    // 페이징 방법(1)
//    @Transactional
//    public Page<Product> getProducts(int pageNum, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNum, pageSize);
//        return productRepository.getProducts(pageable);
//    }

    // 페이징 방법(2)
    @Transactional
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

//    // 페이징 방법(3)
//    @Transactional
//    public List<Product> getProducts(int page, int size) {
//        int offset = (page - 1) * size;  // offset 계산
//        return productRepository.findProductsByPage(size, offset);
//    }
//    @Transactional
//    public int getTotalPages(int size) {
//        int totalProducts = productRepository.countProducts();
//        return (int) Math.ceil((double) totalProducts / size);
//    }

    @Transactional
    public List<Product> getProductsByCategoryId(int categoryId, int page, int pageSize) {
        int offset = (page - 1) * pageSize;  // offset 계산
        return productRepository.getProductsByCategoryId(categoryId, pageSize, offset);
    }
    @Transactional
    public int getTotalPages(int size) {
        int totalProducts = productRepository.countProducts();
        return (int) Math.ceil((double) totalProducts / size);
    }
}
