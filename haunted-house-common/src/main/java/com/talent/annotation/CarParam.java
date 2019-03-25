package com.talent.annotation;

import com.talent.enums.CarParamType;

/**
 * @author guobing
 * @Title: CarParam
 * @ProjectName haunted-house
 * @Description: TODO
 * @date 2019/3/255:17 PM
 */
public @interface CarParam {

    CarParamType type() default CarParamType.BRAND;
}
