package com.first;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class KnightConfigTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(com.first.KnightConfig.class);
        Object bean = annotationConfigApplicationContext.getBean("KnightConfig");
        System.out.println(bean.toString());

    }
}
