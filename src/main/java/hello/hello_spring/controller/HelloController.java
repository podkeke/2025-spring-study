package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    /**
     * "hello" URL 요청을 처리하는 메서드
     * @param model : 데이터를 뷰에 전달하기 위한 객체
     * @return "hello" : 렌더링할 뷰 이름 (templates+{viewname}+'.html')
     */
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // hello.html 뷰를 반환 -> resources/templates/hello.html을 찾아서 렌더링
    }
}
