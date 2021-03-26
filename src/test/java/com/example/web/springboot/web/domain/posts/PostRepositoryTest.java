package com.example.web.springboot.web.domain.posts;

import com.example.web.springboot.domain.posts.Posts;
import com.example.web.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest //자동으로 h2 데이터 베이스 실행
public class PostRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After //단위 테스트 이후에 자동 실행 하는 메소드
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void insertNreadArticle(){
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jjlim@naver.com")
                .build());
        //when
        List<Posts> postsList =postsRepository.findAll();
        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void registBaseTimeEntity(){
        LocalDateTime now = LocalDateTime.of(2021,3,4,2,40,20);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>create Date = " + posts.getCreateDate() + "modified Date =" + posts.getModifiedDate());

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}
