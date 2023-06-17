package org.example.mvc;

import org.example.mvc.controller.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Handler;

public class RequestMappingHendlerMapping implements HandlerMapping{  //2 핸들러매핑
    //  key: /users  , value: UserController
    private Map<HandlerKey , Controller> mapppings = new HashMap<>();   // 키=urlpath
    void init(){ //3 컨트롤러 선택  핸들러키라는 객체에 메소드와 uri를 전달함
       // mapppings.put(new HandlerKey(RequestMethod.GET , "/"),new HomeController());
        mapppings.put(new HandlerKey(RequestMethod.GET , "/users"),new UserListController());
        mapppings.put(new HandlerKey(RequestMethod.POST , "/users"),new UserCreateController());
        mapppings.put(new HandlerKey(RequestMethod.GET , "/user/form"),new ForWardController("/user/form")); ///user/form이 입력되면 /user/form.jsp을 띄운다(단순하게 이동)
    }

    public Controller findHendler(HandlerKey handlerKey){  //urlpath 하고 일치하는 controller를 리턴해주는 메소드   //5
        return mapppings.get(handlerKey);
    }


}
