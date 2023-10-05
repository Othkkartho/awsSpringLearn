package com.learn.awsspringlearn.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @SpringBootTest를 적용하면 @ExtendWith(SpringExtension.class)를 포함하고 있어 생략이 가능
@ExtendWith(SpringExtension.class)                  // JUnit5에서 이전 @RunWith(SpringRunner.class)을 대체
@WebMvcTest(controllers = HelloController.class)    // Web(Spring MVC)에 집중할 수 있는 어노테이션. 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있다. 단, @Service, @Component, @Repository 등은 사용할 수 없다.
public class HelloControllerTest {

    @Autowired              // 스프링 빈(Bean)을 주입
    private MockMvc mvc;    // 개발한 웹 프로그램을 실제 서버에 배포하지 않고도 테스트를 위한 요청을 제공 즉, 웹 API 테스트 할 때 사용, 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트

    @Test                                               // test할 메서드
    @DisplayName("hello가 리턴된다")
    public void returnHello() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello"))            // MockMvc를 통해 /hello 주소로 HTTP GET 요청, 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언
                .andExpect(status().isOk())             // mvc.perform의 결과를 검증, HTTP Header의 Status를 검증, 우리가 흔히 알고 있는 200, 404, 500등의 상태를 검증
                .andExpect(content().string(hello));    // mvc.perform의 결과를 검증, 응답 본문의 내용을 검증, Controller에서 "hello"를 return 하기 때문에 이 값이 맞는지 검증
    }

}
