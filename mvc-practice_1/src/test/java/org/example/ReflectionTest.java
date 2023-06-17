package org.example;

import javassist.tools.reflect.Reflection;
import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @Controller 어노테이션이 설정돼 있는 모든 클래스를 찾아서 출력한다.
 */
public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    
    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWidth(List.of(Controller.class, Service.class));

        logger.debug("beans: [{}]",beans);
    }

    @Test
    void showClass() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        logger.debug("User all declared fields: [{}]" , Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList())); //모든 필드
        logger.debug("User all declared constructors: [{}]" , Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList())); //모든 생성자
        logger.debug("User all declared methods: [{}]" , Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList())); // 모든 메소드
    }

    @Test
    void load() throws ClassNotFoundException {
        //힙영역에서 load되어있는 클래스 객체 가져오는법
        //1
        Class<User> clazz = User.class;

        //2
        User user = new User("name","sj");
        Class<?extends User> clazz2 = user.getClass();

        //3
        Class<?> clazz3 = Class.forName("org.example.model.User");

        logger.debug("clazz: [{}]",clazz);
        logger.debug("clazz2: [{}]",clazz2);
        logger.debug("clazz3: [{}]",clazz3);

        assertThat(clazz==clazz2).isTrue();
        assertThat(clazz2==clazz3).isTrue();
        assertThat(clazz3==clazz).isTrue();
    }

    private static Set<Class<?>> getTypesAnnotatedWidth(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example"); //org.example밑에 모든 클래스

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }
}
