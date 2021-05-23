package com.neu.boot.controller;

import com.neu.boot.bean.Person;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangyx
 * @create 2021-05-23 10:39
 */
@RestController
public class ParameterTestController {

    @GetMapping("/car/{id}/owner/{username}")
    public Map<String,Object> getCar(@PathVariable("id") Integer id,
                                     @PathVariable("username") String name,
                                     @PathVariable Map<String,String> pv,
                                     @RequestHeader("User-Agent") String agent,
                                     @RequestHeader Map<String,String> headers,
                                     @RequestParam("age") Integer age,
                                     @RequestParam Map<String,String> params,
                                     @RequestParam("interest") List<String> interest){
        Map<String, Object> map = new HashMap<>();
        System.out.println(headers);
        map.put("id",id);
        map.put("name",name);
        map.put("pv",pv);
        map.put("age",age);
        map.put("interest",interest);
        map.put("params",params);
        return map;
    }

    @PostMapping("/save")
    public Map PostMethod(@RequestBody String content){
        HashMap<String, Object> map = new HashMap<>();
        map.put("content",content);
        return map;
    }

    //springBoot默认禁止了矩阵变量，需要手动开启
    //修改UrlPathHelper
    //路径不能直接使用/cars/sell,要使用路径变量
    @GetMapping("/cars/{path}")
    public Map carSell(@MatrixVariable("low") Integer low,
                       @MatrixVariable("brand") List<String> brand,
                       @PathVariable String path){
        HashMap<String, Object> map = new HashMap<>();
        map.put("low",low);
        map.put("brand",brand);
        map.put("path",path);
        return map;
    }

    // : /boss/1;age=20/2;age=10
    @GetMapping("/boss/{bossId}/{empId}")
    public Map boss(@MatrixVariable(value = "age",pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(value = "age",pathVar = "empId") Integer empAge){
        HashMap<String, Object> map = new HashMap<>();
        map.put("bossAge",bossAge);
        map.put("empAge",empAge);
        return map;
    }

    @PostMapping("/saveuser")
    public Person saveUser(Person person){
        return person;
    }

}
