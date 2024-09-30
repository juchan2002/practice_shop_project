package org.example.firstshopproject.repository;

import org.example.firstshopproject.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
//    Boolean existsByLoginId(String loginId);
}
