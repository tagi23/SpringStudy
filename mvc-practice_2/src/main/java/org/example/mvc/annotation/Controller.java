package org.example.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})  //클래스에 붙일 수 있음
@Retention(RetentionPolicy.RUNTIME) //런타임까지
public @interface Controller {
}
