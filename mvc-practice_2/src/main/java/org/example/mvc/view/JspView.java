package org.example.mvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JspView implements View{
    private final String name;

    public JspView(String name) {
        this.name = name;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {//화면으로 forward 해주는부분
        model.forEach(request::setAttribute);   //model에 값이 있으면 request에 세팅해달라는코드

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(name); //getRequestDispatcher에서 viewName 으로 req를 얻어옴
        requestDispatcher.forward(request,response);  //찾기
    }
}
