package org.example.mvc;

import org.example.mvc.annotation.RequestMapping;
import org.example.mvc.controller.RequestMethod;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping{
    private final Object[] basePackage;
    private Map<HandlerKey , AnnotationHandler> handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() {  //초기화  (init 의 역할)
        Reflections reflections = new Reflections(basePackage);

        //HomeController
        Set<Class<?>> clazzesWithControllerAnnotation = reflections.getTypesAnnotatedWith(org.example.mvc.annotation.Controller.class, true);

        clazzesWithControllerAnnotation.forEach(clazz ->     //메소드 추출을 위해 돌림
                Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod -> {
                    RequestMapping requestMapping = declaredMethod.getDeclaredAnnotation(RequestMapping.class); //해당하는 컨트롤러가 붙어있는 class를 가지고옴

                    // @RequestMapping(value = "/",method = RequestMethod.GET)
                    Arrays.stream(getRequestMethods(requestMapping))
                            .forEach(requestMethod -> handlers.put(
                                    new HandlerKey(requestMethod , requestMapping.value()), new AnnotationHandler(clazz,declaredMethod)
                            ));
                })
        );
    }

    private RequestMethod[] getRequestMethods(RequestMapping requestMapping) { //get인지 post인지 방식을 리턴하기위한 메소드
        return requestMapping.method();
    }

    @Override
    public Object findHendler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }


}
