package org.example.mvc;

import org.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnnotationHandlerApapter implements HandlerAdepter{
    @Override
    public boolean supports(Object handler) {
        return handler instanceof AnnotationHandler; //AnnotationHandler 이면 handle 실행
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String viewName = ((AnnotationHandler) handler).handel(request, response);
        return new ModelAndView(viewName);
    }
}
