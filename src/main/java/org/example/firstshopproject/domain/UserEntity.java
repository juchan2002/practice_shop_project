package org.example.firstshopproject.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정, MySQL에서 사용
    private int id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "login_id", nullable = false, length = 200)
    private String loginId;

    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @UpdateTimestamp
    @Column(name = "enroll_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime enrollDate;
}
