package com.learn.awsspringlearn.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// JSON을 반환(return)하는 컨트롤러로 만들어 줌
// 예전 비동기 처리시 사용하는 @RequestBody를 각 메서드마다 선언했던 것을 한 번에 사용할 수 있도록 됨
@RestController
public class HelloController {
    // HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어 쥼
    // 예전에는 @RequestMapping(method = RequestMethod.GET)으로 사용되었음
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
