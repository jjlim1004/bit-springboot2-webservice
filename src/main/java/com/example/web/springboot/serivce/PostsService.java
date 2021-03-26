package com.example.web.springboot.serivce;

import com.example.web.springboot.web.dto.PostsListResponseDto;
import com.example.web.springboot.web.dto.PostsResponseDto;
import com.example.web.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.web.springboot.domain.posts.Posts;
import com.example.web.springboot.domain.posts.PostsRepository;
//import com.example.web.springboot.web.dto.PostsListResponseDto;
//import com.example.web.springboot.web.dto.PostsResponseDto;
import com.example.web.springboot.web.dto.PostsSaveRequestDto;
//import com.example.web.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당사용자가 없습니다. id= " + id));
        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 사용자가 없습니다. id = "+ id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) //readOnly 로 정하면 조회 기능에 올인하기 때문에 읽는 속도가 빠르다.
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

}
