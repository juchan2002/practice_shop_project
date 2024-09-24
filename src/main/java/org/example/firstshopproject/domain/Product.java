package org.example.firstshopproject.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@NoArgsConstructor
@Data   
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정, MySQL에서 사용
    private int id;

    @Column(name = "category_id", nullable = false)
    private int categoryId;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "price", nullable = false, length = 200)
    private String price;

    @Column(name = "comment", length = 2000)
    private String comment;

    @Column(name = "img", length = 200)
    private String img;
}
