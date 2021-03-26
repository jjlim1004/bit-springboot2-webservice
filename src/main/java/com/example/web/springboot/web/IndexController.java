package com.example.web.springboot.web;

//import com.example.web.springboot.config.auth.LoginUser;
//import com.example.web.springboot.config.auth.dto.SessionUser;
import com.example.web.springboot.domain.posts.PostsRepository;
import com.example.web.springboot.serivce.PostsService;
import com.example.web.springboot.web.dto.PostsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @Autowired
    PostsService postsService;

    @GetMapping("/")
    public String index(Model model){//, @LoginUser SessionUser user){
        model.addAttribute("posts",postsService.findAllDesc());
        return "index"; //src/main/resources/templates/index.mustache
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id ,Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }


}
