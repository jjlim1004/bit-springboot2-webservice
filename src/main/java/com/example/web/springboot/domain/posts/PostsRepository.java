package com.example.web.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//JPA 는 인터페이스로 만든다.
public interface PostsRepository extends JpaRepository<Posts,Long> { //Entity 타입, Entity의 pk 키 타입

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();


}
