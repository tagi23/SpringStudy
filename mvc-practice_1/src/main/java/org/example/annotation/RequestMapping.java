package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})  //클래스랑 메소드에도 붙일수있다.
@Retention(RetentionPolicy.RUNTIME) // 유지기간은 runtime기간까지
public @interface RequestMapping {
    String value() default "";

    RequestMethod[] method() default {};
}
