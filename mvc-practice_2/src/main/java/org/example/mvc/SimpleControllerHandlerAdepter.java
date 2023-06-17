package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleControllerHandlerAdepter implements HandlerAdepter{
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof Controller);   //전달된 핸들러가 Controller 인터페이스를 구현한 구현체라면 지원을 해주겠다.
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response , Object handler) throws Exception {  //핸들을 호출하면
        String viewName = ((Controller)handler).handleRequest(request,response);  //컨트롤러로 타입캐스팅후 request,response 를 던져주겠다/  (4>5) 선택받은 컨트롤러가 viewname 리턴
        return new ModelAndView(viewName);
    }
}
