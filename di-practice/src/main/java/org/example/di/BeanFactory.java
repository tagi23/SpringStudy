package org.example.di;

import javassist.tools.reflect.Reflection;
import org.example.annotation.Inject;
import org.example.controller.UserController;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {
    private final Set<Class<?>> preInstantiatedClazz;

    private Map<Class<?>, Object> beans = new HashMap<>();   //클래스타입객체 = key , 클래스타입객체로 생성되는 인스턴스 = value

    public BeanFactory(Set<Class<?>> preInstantiatedClazz) {
        this.preInstantiatedClazz = preInstantiatedClazz;
        initialize();  //beans 초기화
    }

    private void initialize() {
        for(Class<?>clazz : preInstantiatedClazz){
            Object instance = createInstance(clazz); //인스턴스 생성
            beans.put(clazz,instance);
        }
    }

    //UserController
    //UserService
    private Object createInstance(Class<?> clazz) {
        //생성자  (인스턴스를 생성하려면 생성자가 있어야함)
        Constructor<?> constructor = findConstructor(clazz);  //클래스타입객체로 constructor 조회하고

        //파라미터 (생성자에 들어갈 파라미터)
        List<Object> parameters = new ArrayList<>(); //파라미터를 담을 list 생성
        for(Class<?> typeClass : constructor.getParameterTypes()){  //위에서 조회해온 constructor 파라미터 타입들을 가지고옴
            //UserService
            parameters.add(getParameterByClass(typeClass));   // 파라미터타입에 대한 클래스타입의 객체를 가지고왔음 클래스타입의객체에 대한 인스턴스를 가지고오는 메소드
        }
        //인스턴스 생성
        try {
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Constructor<?> findConstructor(Class<?> concreteClass) {
        Constructor<?> constructor = BeanFactoryUtils.getInjectedConstructor(concreteClass);

        if (Objects.nonNull(constructor)) {
            return constructor;
        }

        return concreteClass.getConstructors()[0];
    }

    
    private Object getParameterByClass(Class<?> typeClass) {
        Object instanceBean = getBean(typeClass);

        if (Objects.nonNull(instanceBean)){  //instanceBean이 있으면 반환
            return instanceBean;
        }
        return createInstance(typeClass);  //없으면 typeClass를 다시 만듦 (재귀함수)
    }

    
    public <T> T getBean(Class<T> requiredType) {
        return (T) beans.get(requiredType);
    }
}
