package com.neu.boot.converter;

import com.neu.boot.bean.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author yangyx
 * @create 2021-05-23 21:46
 */
public class MyMessageConverter implements HttpMessageConverter<Person> {

    @Override
    public boolean canRead(Class<?> aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> aClass, MediaType mediaType) {
        return aClass.isAssignableFrom(Person.class);
    }

    /**
     * 服务器要统计MessageConverter能写出哪些类型
     * 自定义类型：application/x-mytype
     * */
    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return MediaType.parseMediaTypes("application/x-mytype");
    }

    @Override
    public Person read(Class<? extends Person> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Person person, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
       //自定义数据
        String data = person.getUserName() + ";" + person.getAge() + ";" + person.getBirth();
        //写出数据
        OutputStream body = httpOutputMessage.getBody();
        body.write(data.getBytes());
    }
}
