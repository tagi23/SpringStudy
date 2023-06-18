package org.example.di;

import org.example.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactoryUtils {
    public static Constructor<?> getInjectedConstructor(Class<?> clazz) {
        //리플랙션에서 제공해주는 리플랙션 유틸에 getAllConstructors 메소드가 클래스타입의 객체의 모든 Constructor를 가지고온다 단 Inject어노테이션만 붙은 생성자만
        Set<Constructor> injectedConstructors = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotation(Inject.class));
        if(injectedConstructors.isEmpty()){ //없으면 널 반환
            return null;
        }
        return injectedConstructors.iterator().next();
    }
}
