package com.neu.boot.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author yangyx
 * @create 2021-05-22 21:49
 */
@RestController
public class HelloController {

    @RequestMapping("/bug.jpg")
    public String hello(){
        return "car";
    }
    //@RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping("/user")
    public String getUser(){
        return "GET-User";
    }
    //@RequestMapping(value = "/user", method = RequestMethod.POST)
    @PostMapping("/user")
    public String postUser(){
        return "POST-User";
    }
    //@RequestMapping(value = "/user", method = RequestMethod.DELETE)
    @DeleteMapping("/user")
    public String deleteUser(){
        return "DELETE-User";
    }
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String putUser(){
        return "PUT-Userr";
    }
}
