package org.example.firstshopproject.service;

import lombok.RequiredArgsConstructor;
import org.example.firstshopproject.domain.UserEntity;
import org.example.firstshopproject.dto.JoinDTO;
import org.example.firstshopproject.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {

        UserEntity userEntity = new UserEntity();

        userEntity.setName(joinDTO.getName());
        userEntity.setLoginId(joinDTO.getLoginId());
        userEntity.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));

        userRepository.save(userEntity);
    }
}
