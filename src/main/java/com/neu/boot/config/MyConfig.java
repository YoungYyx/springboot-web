package com.neu.boot.config;

import com.neu.boot.bean.Pet;
import com.neu.boot.converter.MyMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangyx
 * @create 2021-05-22 23:19
 */
@Configuration(proxyBeanMethods = false)
public class MyConfig {


    /*
    这里设置  RESTFUL 的隐藏方法的参数名 默认: _method
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        hiddenHttpMethodFilter.setMethodParam("_m");
        return hiddenHttpMethodFilter;
    }*/

    //开启矩阵变量 方式一，Myconfig类实现WebMvcConfigurer接口，并修改configurePathMath方法，修改其中UrlPathHelper的属性为false
//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        UrlPathHelper urlPathHelper = new UrlPathHelper();
//        //设置不移除;开启矩阵变量
//        urlPathHelper.setRemoveSemicolonContent(false);
//        configurer.setUrlPathHelper(urlPathHelper);
//    }

    //开启矩阵变量方式二,声明一个WebMvcConfigurer组件
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {

            //设置矩阵变量生效
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }

            //自定义类型转换器,转换字符串到Pet
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new Converter<String, Pet>() {
                    @Override
                    public Pet convert(String s) {
                       if(!StringUtils.isEmpty(s)){
                           Pet pet = new Pet();
                           String[] split = s.split(",");
                           pet.setName(split[0]);
                           pet.setAge(Integer.parseInt(split[1]));
                           System.out.println(pet);
                           return pet;
                       }
                       return null;
                    }
                });
            }

            //扩展已有的messageconverter
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new MyMessageConverter());
            }

            //自定义内容协商策略
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                Map<String, MediaType> mediaTypes = new HashMap<>();
                //指定支持解析哪些参数以及对应媒体类型
                mediaTypes.put("json",MediaType.APPLICATION_JSON);
                mediaTypes.put("xml",MediaType.APPLICATION_XML);
                mediaTypes.put("mytype",MediaType.parseMediaType("application/x-mytype"));
                ParameterContentNegotiationStrategy parameterStrategy = new ParameterContentNegotiationStrategy(mediaTypes);
                HeaderContentNegotiationStrategy headerStrategy = new HeaderContentNegotiationStrategy();
                //只添加参数策略会覆盖默认的策略，导致基于请求头的策略失效，所以需要添加一个基于请求头的
                configurer.strategies(Arrays.asList(parameterStrategy,headerStrategy));

            }
        };
    }
}
