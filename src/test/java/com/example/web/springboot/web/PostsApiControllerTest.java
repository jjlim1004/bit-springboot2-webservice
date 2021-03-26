package com.example.web.springboot.web;

import com.example.web.springboot.web.dto.PostsSaveRequestDto;
import com.example.web.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.example.web.springboot.domain.posts.*;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.security.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
//@WebMvcTest 는 JPA 가 작동하지 않는다. SpringBoot에서 제공하는 SpringBootTest로 해야JPA가 작동한다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //내부 톰캣이 실행되면서 , 랜덤 포트를 할당
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate; //JPA 기능 test를 함께 실행하기 위해서 생성, SpringBootTest 가 제공하는 클래스

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before //테스트가 실행되기 이전에 실행되어야 할 것
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
//                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
//    @WithMockUser(roles="USER")
    public void registerPosts() throws Exception{
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" +port + "/api/v1/posts";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper()
                        .writeValueAsString(requestDto))) //db에 저장할 콘텐트 생성
                .andExpect(status().isOk());
        //then
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
//    @WithMockUser(roles="USER")
    public void updatePosts() throws Exception{
        Posts savePosts =postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savePosts.getId();
        String expectedTitle = "title2";
        String expectedContent= "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title("title2")
                .content("content2")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
        //when
        mvc.perform(put(url).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
        //then
        List<Posts> all =postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }


}
