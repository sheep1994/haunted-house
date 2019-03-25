package com.talent.annotation;

import com.talent.enums.DicType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author guobing
 * @Title: Dic
 * @ProjectName haunted-house
 * @Description: 字典注解，只能标注在属性字段上
 * @date 2019/3/255:07 PM
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Dic {

    /**
     * 字典类型
     * @return
     */
    DicType type() default DicType.NONE;

    /**
     * 每个类型下可能有参数,例如车型有车系/车型/品牌区别
     */
    String param() default "";

    /**
     * 关联属性 没有则默认以添加注解字段的前缀+Code
     */
    String relativeField() default "";
}
