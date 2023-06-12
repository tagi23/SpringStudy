package SjStudy.SpringStudy_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCotroller {
    @GetMapping("/")  //첫번째 도메인   컨테이너가 얘를 먼저 인식해 기존의 html은 무시됨
    public String home(){
        return "home";
    }
}
