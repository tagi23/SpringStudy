package SjStudy.SpringStudy_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller    //컨트롤러 어노테이션
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data" , "김철수입니다. 반갑습니다");   //"hello 반갑습니다" 라는 밸류가 data로 치환
        return "hello";
    }
    @GetMapping("hello-mvc")
    public String hellomvc(@RequestParam("name")String name , Model model){  //외부에서 파라미터를 받아와서 그걸 모델에 담는다
        model.addAttribute("name",name);  // 모델에 담은 name을 "name"이라는 키에 담는다.
        return  "hello-template"; // html에 보낸다. ( hello-template  << model(name:value))
    }                           // > viewResolver에 보낸다
    @GetMapping("hello-string")
    @ResponseBody   //http에서 body부에 데이터를 직접 넣어줌
    public String helloString(@RequestParam("name")String name){
        return "hello " + name;   //"hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody    //데이터를 얻을때 api를 많이씀
    public Hello helloApi(@RequestParam("name")String name){  //json으로 보내보기
        Hello hello = new Hello();  //객체로 보내기
        hello.setName(name);
        return hello;
    }
        static class Hello{  //클래스 만들기
            private String name;
            public String getName() {  //getter 생성
                return name;
            }
            public void setName(String name) {  //setter 생성
                this.name = name;
            }


        }

}

