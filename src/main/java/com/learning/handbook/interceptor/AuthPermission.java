package com.learning.handbook.interceptor;

import java.lang.annotation.*;

/**
 * Created by xiaodong on 2016/2/29.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPermission {
    boolean validate() default true;
    String value() default "";
}
