package com.talent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author guobing
 * @Title: Dictionary
 * @ProjectName haunted-house
 * @Description: TODO
 * @date 2019/3/255:18 PM
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Dictionary {

    /**
     * 字典里面的type
     * @return
     */
    String type() default "";

    /**
     *
     * 对应的code的属性，不写默认 字段名称-Name的值
     * @return
     */
    String relativeCodeField() default "";
}
