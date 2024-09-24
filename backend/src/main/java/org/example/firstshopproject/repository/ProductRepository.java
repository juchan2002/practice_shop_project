package org.example.firstshopproject.repository;

import org.example.firstshopproject.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> getProductsById(int productId);

//    /// 페이징 방법(3)
//    @Query(value = "SELECT * FROM product ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
//    List<Product> findProductsByPage(@Param("limit") int limit, @Param("offset") int offset);
//    @Query(value = "SELECT COUNT(*) FROM product", nativeQuery = true)
//    int countProducts();
}
