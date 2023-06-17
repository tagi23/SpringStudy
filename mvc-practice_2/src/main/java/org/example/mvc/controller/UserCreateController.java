package org.example.mvc.controller;

import org.example.mvc.model.User;
import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateController implements Controller{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserRepository.save(new User(request.getParameter("userId"),request.getParameter("name")));  //요청으로 받은 userid와 name을 저장
        //user 추가
        return "redirect:/users";  //users 라는 클라이언트 쪽으로 응답을 내려줌
    }
}
