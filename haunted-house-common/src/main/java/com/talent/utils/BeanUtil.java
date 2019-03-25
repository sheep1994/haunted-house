package com.talent.utils;

import com.google.common.base.Preconditions;
import com.talent.annotation.Dic;
import com.talent.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author guobing
 * @Title: BeanUtil
 * @ProjectName haunted-house
 * @Description: bean 拷贝工具类
 * @date 2019/3/2511:53 AM
 */
@Component
@Slf4j
public class BeanUtil {

    /**
     * 获取空属性名称
     * @param source: 必须是实例化后的Object对象
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        /**
         * BeanWrapper是Spring对Bean的包装
         */
        final BeanWrapper src = new BeanWrapperImpl(source);
        /**
         * 获取bean中所有属性的描述
         */
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object propertyValue = src.getPropertyValue(pd.getName());
            if (Objects.isNull(propertyValue)) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 单纯不拷贝空值
     * @param source：实例化的Object对象
     * @param target：目标对象
     * @param ignoreProperties：忽略的属性
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target, String... ignoreProperties) {
        BeanUtils.copyProperties(source, target, StringUtils.join(getNullPropertyNames(source), ignoreProperties));
    }

    /**
     * 不拷贝空值
     * @param source
     * @param target
     */
    public static void copyIgnoreNullValue(Object source, Object target) {
        // 在进行业务代码前进行的前置判断，避免了冗余的if语句。检查对象是否为空
        Preconditions.checkNotNull(source, "source must not be null");
        // 获取bean的所有字段（包括父类）
        List<Field> fields = getAllFields(source);
        for (Field field : fields) {
            try {
                // 构建一个属性描述器
                PropertyDescriptor sproperty = new PropertyDescriptor(field.getName(), source.getClass());
                // 从属性描述器中获取getter方法
                Method readMethod = sproperty.getReadMethod();
                if (Objects.isNull(readMethod)) {
                    continue;
                }
                Object value = readMethod.invoke(source);
                if (Objects.isNull(value)) {
                    continue;
                }
                PropertyDescriptor tproperty = new PropertyDescriptor(field.getName(), target.getClass());
                // 从属性描述器中获取setter方法
                Method writeMethod = tproperty.getWriteMethod();
                if (Objects.isNull(writeMethod)) {
                    continue;
                }
                writeMethod.invoke(target, value);
            } catch (Exception e) {
                log.error("method copyIgnoreNullValue error.", e);
                e.printStackTrace();
            }
        }
    }

    /**
     * 拷贝列表
     * @param source
     * @param target
     * @param targetClass
     * @param <T>
     * @param <E>
     */
    public static <T, E> void copyList(List<T> source, List<E> target, Class<?> targetClass) {
        if (CollectionUtils.isEmpty(source)) {
            return ;
        }
        if (Objects.isNull(target)) {
            return ;
        }
        for (T t : source) {
            try {
                @SuppressWarnings("unchecked")
                E e = (E) targetClass.newInstance();
                BeanUtils.copyProperties(t, e);
                target.add(e);
            } catch (Exception e) {
                log.error("method copyList error", e);
            }
        }
    }

    /**
     * 获取bean的所有属性字段
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<Field> getAllFields(T t) {
        // 如果传入的对象为空，返回
        if (Objects.isNull(t)) {
            return new ArrayList<>();
        }
        T loop = t;
        List<Field> fields = new ArrayList<>();
        try {
            while (true) {
                Field[] superFields = loop.getClass().getDeclaredFields();
                if (Objects.nonNull(superFields)) {
                    for (Field field : superFields) {
                        /**
                         * 忽略序列号
                         */
                        if ("serialVersionUID".equals(field.getName())) {
                            continue;
                        }
                        fields.add(field);
                    }
                }
                // 直到父类为空，才跳出
                if (Objects.isNull(loop.getClass().getSuperclass())) {
                    break;
                }
                // 父类属性
                loop = (T) loop.getClass().getSuperclass().newInstance();
            }
        } catch (Exception e) {
            log.error("method getAllFields error. ", e);
        }
        return fields;
    }

    /**
     * 即系注解并拷贝
     * @param source
     * @param target
     */
    public static void copyWithParseAnnotation(Object source, Object target) {
        if (source == null) {
            return;
        }
        if (target == null) {
            return;
        }
        BeanUtils.copyProperties(source, target);
        // 获取属性字段
        Field[] fields = target.getClass().getDeclaredFields();
        if (Objects.isNull(fields)) {
            return ;
        }
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Dic.class)) {
                continue;
            }
            try {
                switch (field.getAnnotation(Dic.class).type()) {
                    case SHOP:
                        break;
                    case USER:
                        break;
                    case USER_PHONE:
                        break;
                    case DICTIONARY:
                        break;
                    case CAR_PARAM:
                        break;
                    case CUSTOMER_SOURCE:
                        break;
                    case NONE:
                    default:
                        break;
                }
            } catch (Exception e) {
                log.error("method copyWithParseAnnotation error.", e);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        User user = new User(1L,  "123456", 12, "江湖路", new Date(), 10000d, "江左盟", "1123123123123123123");

    }
}
