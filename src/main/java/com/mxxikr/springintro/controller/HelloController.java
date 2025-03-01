package com.mxxikr.springintro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Controller: 스프링이 실행될 때 스프링 컨테이너에 이 클래스의 객체를 생성하여 넣어둠
 * @GetMapping: HTTP GET 요청을 처리하는 API
 * @ResponseBody: HTTP 응답 바디에 직접 문자 내용을 넣어주겠다는 의미 - 기본적으로 JSON 형태로 반환
 * @Controller + @ResponseBody = @RestController
 * @RequestParam: 외부에서 파라미터를 받음
 *  - required=false: 파라미터가 없어도 됨
 *  - defaultValue="": 파라미터가 없으면 빈 문자열로 처리
 *
 *  model: 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장 가능
 *  model.addAttribute: key, value 형태로 저장
  */
@Controller
public class HelloController {
    // URL 매핑 - 웹 브라우저에서 /hello로 요청이 오면 이 메서드를 실행
    @GetMapping("hello")
    public String hello(Model model) {

        model.addAttribute("data", "hello!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {

        model.addAttribute("name", name);
        return "hello-template";
    }


    // http BODY에 문자 내용을 직접 반환
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // hello string
    }

    // 객체 반환
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
 }
