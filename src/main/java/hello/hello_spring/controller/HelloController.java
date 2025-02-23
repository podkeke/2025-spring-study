package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    /**
     * "hello" URL 요청을 처리하는 메서드
     *
     * @param model : 데이터를 뷰에 전달하기 위한 객체
     * @return "hello" : 렌더링할 뷰 이름 (templates+{viewname}+'.html')
     */
    //http://localhost:8080/hello-static.html
    @GetMapping("hello") // 클라이언트가 /hello로 GET 요청을 보내면 hello() 메서드가 실행
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); //data라는 이름으로 "hello!!"값을 저장
        return "hello"; // hello.html 뷰를 반환 -> resources/templates/hello.html을 찾아서 렌더링
    }


    /**
     * "hello-mvc" URL 요청을 처리하는 메서드
     *
     * @param name  요청 파라미터 (URL에서 전달된 값, 예: "?name=spring")
     * @param model 데이터를 뷰에 전달하기 위한 객체
     * @return "hello-template" : 렌더링할 뷰의 이름 (templates/hello-template.html)
     */
    //http://localhost:8080/hello-mvc?name=spring
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, // URL의 "name" 파라미터 값을 받아옴 (예: ?name=spring → name = "spring")
                           Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }


    /**
     * "hello-string" URL 요청을 처리하는 메서드
     *
     * @param name  요청 파라미터 (예: "?name=spring" → name = "spring")
     * @return "hello spring"과 같은 문자열을 응답 (HTML 뷰가 아닌, 데이터 자체를 반환)
     *
     * - @ResponseBody가 붙어 있어 **뷰를 거치지 않고** HTTP 응답 본문(body)에 데이터를 직접 반환함
     */
    //http://localhost:8080/hello-string?name=spring
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }


    /**
     * "hello-api" URL 요청을 처리하는 메서드
     *
     * @param name 요청 파라미터 (예: "?name=spring" → name = "spring")
     * @return Hello 객체를 JSON 형식으로 응답 : {"name":"spring"}
     *
     * - @ResponseBody가 적용되면, 객체(Hello)를 반환할 때 JSON 형태로 변환됨
     */
    //http://localhost:8080/hello-api?name=spring
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
            this.name= name;
        }
    }
}







