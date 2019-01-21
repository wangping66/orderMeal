package com.query;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryAnnotation {
    String sqlName() ;
    //String queryType() default "equals";
    QueryType queryType() default QueryType.EQUALS;
    //查询 and连接list 获取or连接list
    String connectType() default "add";

}
