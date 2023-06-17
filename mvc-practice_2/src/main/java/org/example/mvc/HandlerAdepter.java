package org.example.mvc;

import org.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdepter {
    boolean supports(Object handler); //전달된 핸들러를 지원해주는 어뎁터인지

    ModelAndView handle(HttpServletRequest request , HttpServletResponse response , Object handler) throws Exception; //지원해준다면 수행
}
