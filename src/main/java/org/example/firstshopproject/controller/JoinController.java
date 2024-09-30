package org.example.firstshopproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.firstshopproject.dto.JoinDTO;
import org.example.firstshopproject.service.JoinService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // React 애플리케이션의 주소
public class JoinController {

    private final JoinService joinService;
    @PostMapping("/join")
    public String joinProcess(@RequestBody JoinDTO joinDTO) {

        System.out.println("회원가입 정보");
        System.out.println(joinDTO.getName());
        System.out.println(joinDTO.getLoginId());
        System.out.println(joinDTO.getPassword());

        joinService.joinProcess(joinDTO);

        return "회원가입 성공";
    }
}
