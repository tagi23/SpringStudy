package org.example.di;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BeanFactoryTest {
    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach  //세팅
    void setUp() {
        reflections = new Reflections("org.example"); //초기화
        //UserContoroller , UserService
        Set<Class<?>> preInstantiatedClazz = getTypesAnnotatedWith(Controller.class , Service.class);  //에노테이션을 파라미터를 던져서 애노테이션의 클래스 타입 객체를 리턴
        // (만들지않은 메소드지만 미리 정의하는 방식) top down 방식)
        beanFactory = new BeanFactory(preInstantiatedClazz);
    }
    private Set<Class<?>> getTypesAnnotatedWith(Class<?extends Annotation>...annotations) {  //Annotation의 객체라 여러개가 들어올 수 있음
        Set<Class<?>> beans = new HashSet<>();
        for (Class<? extends Annotation> annotation: annotations){   //Controller애노테이션과 , Service애노테이션을 받아서
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));  //"org.example" 밑에 있는 Controller애노테이션과 Service애노테이션을 조회하고 beans에 추가
        }
        return beans;
    }

    @Test
    void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);// UserController.class객체 빈 꺼내기

        assertThat(userController).isNotNull();
        assertThat(userController.getUserService()).isNotNull();
    }
}