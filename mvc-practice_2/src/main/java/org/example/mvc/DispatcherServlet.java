package org.example.mvc;

import org.example.mvc.controller.RequestMethod;
import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.ModelAndView;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/") //어떠한 경로라도 DispatcherServlet이 호출되게
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private List<HandlerMapping> handlerMappings;

    private List<HandlerAdepter> handlerAdepters;

    private List<ViewResolver> viewResolvers;

    @Override
    public void init() throws ServletException {  //톰켓이 서블릿을 생성할때 메소드에서 초기화
        RequestMappingHendlerMapping rmhm = new RequestMappingHendlerMapping();
        rmhm.init();

        AnnotationHandlerMapping ahm = new AnnotationHandlerMapping("org.example");
        ahm.initialize();  //초기화

        handlerMappings = List.of(rmhm, ahm);  //핸들러매핑을 리스트로 받는이유
        handlerAdepters = List.of(new SimpleControllerHandlerAdepter() , new AnnotationHandlerApapter());
        viewResolvers = Collections.singletonList(new JspViewResolver());
    }

    //1.DispatcherServlet > 2.handlerMapping > 3.handlerAdepters > 4.controller > 5.viewName->handlerAdepters 6.ViewResolver > 7.DispatcherServlet > View
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("[DispatcherServlet] Service Start");
        RequestMethod requestMethod = RequestMethod.valueOf(req.getMethod());
        String requestURI = req.getRequestURI();


            Object hendler = handlerMappings.stream()    //rmhm을 통해서 hendler를 찾기  (1>2)     list형태
                    .filter(hm -> hm.findHendler(new HandlerKey(requestMethod, requestURI)) != null) //list에서 해당하는 대상 찾기
                    .map(hm -> hm.findHendler(new HandlerKey(requestMethod, requestURI))) //map으로 변환
                    .findFirst() //하나찾기
                    .orElseThrow(()->new ServletException("No handler for["+requestMethod+requestURI+"]"));
        try {
            //redirect vs forward
            HandlerAdepter handlerAdepter = handlerAdepters.stream()  //  적절한 3 > 4 > 5
                    .filter(ha -> ha.supports(hendler))  //hendler 지원여부
                    .findFirst()  //있으면 찾기
                    .orElseThrow(() -> new ServletException("No adapter for handler [" + hendler + "]")); //아니면 예외

            ModelAndView modelAndView = handlerAdepter.handle(req, resp, hendler);  //종합 데이터를 어댑터로 modelAndView 에 저장  (3) 적절한 핸들러 전달

            for (ViewResolver viewResolver : viewResolvers) {   //(6) viewResolver를통해 적절한 view를 리턴
                View view = viewResolver.resolveView(modelAndView.getViewName());  //viewName에 해당하는 view를 받아온다
                view.render(modelAndView.getModel(), req, resp);  //(7>8)
            }
        } catch (Exception e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
            throw new ServletException(e);
        }
    }
}
