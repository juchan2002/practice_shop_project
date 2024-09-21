package org.example.firstshopproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.firstshopproject.domain.Product;
import org.example.firstshopproject.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // React 애플리케이션의 주소
public class ProductController {

    private final ProductService productService;

//    // 페이징 방법(1)
//    //Pageable 인터페이스를 사용하는데, 직접 pageNum을 정의하고 파라미터로 받음
//    @GetMapping("/products")
//    public Page<Product> getProducts(@RequestParam(defaultValue = "1") int pageNum){
//        int pageSize = 10;
//        return productService.getProducts(pageNum-1, pageSize);
//    }

    // 페이징 방법(2)
    // 받은 파라미터를 pageable 인터페이스 안에 넣고 모든 걸 해결.
    // pageable 인터페이스는 Spring Data JPA 에서 사용 가능!
    @GetMapping("/products")
    public Page<Product> getProducts(Pageable pageable){
        return productService.findAll(pageable);
    }

//    // 페이징 방법(3)
//    // pageable 인터페이스를 사용하지 않고 직접 offset과 limit을 이용하여 구현
//    @GetMapping("/products")
//    public Map<String, Object> getProducts(@RequestParam(defaultValue = "1") int page) {
//        int pageSize = 10;  // 한 페이지당 10개의 상품
//
//        List<Product> products = productService.getProducts(page, pageSize);
//        int totalPages = productService.getTotalPages(pageSize);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("products", products);
//        response.put("currentPage", page);
//        response.put("totalPages", totalPages);
//
//        return response;
//    }

    @GetMapping("/products/{product_id}")
    public Product getProductsById(@PathVariable int product_id){
        return productService.getProductsById(product_id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + product_id));
    }
}
