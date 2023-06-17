package org.example.mvc.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    private Object view;
    private Map<String , Object> model = new HashMap<>();  //model

    public ModelAndView(String viewName) {
        view = viewName;
    }

    public Map<String,?> getModel() {
        return Collections.unmodifiableMap(model);  //불변으로 리턴
    }

    public String getViewName() {
        return (this.view instanceof String ? (String) this.view : null);    //view가 string이라면  view반환 아니라면 null반환
    }
}
