package com.talent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author guobing
 * @Title: CarModel
 * @ProjectName haunted-house
 * @Description: TODO
 * @date 2019/3/255:17 PM
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CarModel {
}
