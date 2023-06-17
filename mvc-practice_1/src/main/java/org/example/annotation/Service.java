package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) //클래스에다가 컨트롤러 annotation 붙이기위해서 type를 지정함
@Retention(RetentionPolicy.RUNTIME) // 유지기간은 runtime기간까지
public @interface Service {
}
