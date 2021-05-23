package com.neu.boot.controller;

import com.neu.boot.bean.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author yangyx
 * @create 2021-05-23 15:49
 */
@RestController
public class ResponseTestControler {

    @ResponseBody
    @GetMapping("/getperson")
    public Person getPerson(){
        Person person = new Person();
        person.setUserName("name");
        person.setAge(13);
        person.setBirth(new Date());
        return person;
    }


}
