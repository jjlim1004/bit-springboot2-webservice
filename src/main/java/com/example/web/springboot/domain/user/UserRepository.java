package com.example.web.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    //Optional은 무엇을 판단할 때 사용 , User가 생성되어 있는지 여부를 판단

}
