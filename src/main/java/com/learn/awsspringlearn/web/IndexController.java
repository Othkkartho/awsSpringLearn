package com.learn.awsspringlearn.web;

import com.learn.awsspringlearn.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {  // Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다. postsService.findAllDesc()에서 가져온 결과를 "posts"로 index.html에 전달.
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }
}
