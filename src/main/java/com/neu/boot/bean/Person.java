package com.neu.boot.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author yangyx
 * @create 2021-05-23 14:36
 */
@Data
public class Person {
    private String userName;
    private Integer age;
    private Date birth;
    private Pet pet;
}
