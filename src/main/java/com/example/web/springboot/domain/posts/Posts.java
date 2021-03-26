package com.example.web.springboot.domain.posts;

import com.example.web.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {//클래스 이름이 테이블 이름이 된다.
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content,String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content ){
        this.title = title;
        this.content = content;
    }
}
