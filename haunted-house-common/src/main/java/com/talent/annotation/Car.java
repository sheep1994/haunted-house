package com.talent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author guobing
 * @Title: Car
 * @ProjectName haunted-house
 * @Description: car注解，标注在属性字段上
 * @date 2019/3/255:15 PM
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Car {

    String carIdField() default "";
}
