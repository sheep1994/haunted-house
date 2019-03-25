package com.talent.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author guobing
 * @Title: User
 * @ProjectName haunted-house
 * @Description: 用户实体类
 * @date 2019/3/252:12 PM
 */
@Data
public class User implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 地址
     */
    private String address;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 薪资
     */
    private Double salary;

    /**
     * 居住城市
     */
    private String city;

    /**
     * 身份证ID
     */
    private String idCard;

    public User(Long id, String password, Integer age, String address, Date birthday, Double salary, String city, String idCard) {
        this.id = id;
        this.password = password;
        this.age = age;
        this.address = address;
        this.birthday = birthday;
        this.salary = salary;
        this.city = city;
        this.idCard = idCard;
    }
}
