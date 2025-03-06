package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 루트 URL("/")로 GET 요청이 들어오면 home() 메서드 실행
    public String home() {
        return "home"; //resources/templates/home.html을 찾음
    }
}