package com.learn.awsspringlearn.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @SpringBootTest를 적용하면 @ExtendWith(SpringExtension.class)를 포함하고 있어 생략이 가능
@MockBean(JpaMetamodelMappingContext.class)
@ExtendWith(SpringExtension.class)                  // JUnit5에서 이전 @RunWith(SpringRunner.class)을 대체
@WebMvcTest(controllers = HelloController.class)    // Web(Spring MVC)에 집중할 수 있는 어노테이션. 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있다. 단, @Service, @Component, @Repository 등은 사용할 수 없다.
public class HelloControllerTest {

    @Autowired              // 스프링 빈(Bean)을 주입
    private MockMvc mvc;    // 개발한 웹 프로그램을 실제 서버에 배포하지 않고도 테스트를 위한 요청을 제공 즉, 웹 API 테스트 할 때 사용, 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트

    @Test                                                   // test할 메서드
    @DisplayName("hello가 리턴된다")
    public void returnHello() {
        String hello = "hello";
        try {
            mvc.perform(get("/hello").with(oauth2Login()))
                    .andExpect(status().isOk())
                    .andExpect(content().string(hello));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void returnHelloDto() {
        String name = "hello";
        int amount = 1000;

        try {
            mvc.perform(get("/hello/dto").with(oauth2Login()).param("name", name).param("amount", String.valueOf(amount)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(name)))
                    .andExpect(jsonPath("$.amount", is(amount)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
