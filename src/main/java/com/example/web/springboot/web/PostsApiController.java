package com.example.web.springboot.web;

import com.example.web.springboot.serivce.PostsService;
import com.example.web.springboot.web.dto.PostsResponseDto;
import com.example.web.springboot.web.dto.PostsSaveRequestDto;
import com.example.web.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import com.example.web.springboot.web.dto.PostsListResponseDto;
//import com.example.web.springboot.web.dto.PostsUpdateRequesetDto;



import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    //RequiredArgsConstructor 있으면 생성자가 자동생성, 생성자로 받는다.
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

//    @GetMapping("/api/v1/posts/list")
//    public List<PostListResponseDto> findAll(){
//        return postsService.findAllDesc();
//    }




}
