package com.query;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.common.exception.CommonException;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 数据库字段与实体类之间字段相互转换
 * @author ellen.zhangyu
 */
public class ConvertClassData {
    /**
     * 实体类与数据库字段绑定
     * @return
     */
    public static <T> Map<String,String> classToData(T model){
        Map<String,String> maps = new HashMap<String,String>();
        String mapValue = null;
        //获取表名
        if (model.getClass().getAnnotation(TableName.class) == null){
            return maps;
        }
        String tableName= model.getClass().getAnnotation(TableName.class).value();

        //同时获取到父类的字段
        List<Field> fields = new ArrayList<>() ;
        Class temp = model.getClass();
        while (temp != null){
            fields.addAll(Arrays.asList(temp.getDeclaredFields()));
            temp = temp.getSuperclass();
        }

        for (Field field:fields){
            boolean isStatic=Modifier.isStatic(field.getModifiers());
            //排除静态变量
            if (isStatic)
                continue;
            if (field.getAnnotation(TableField.class) != null)
                mapValue = field.getAnnotation(TableField.class).value();
            else if (field.getAnnotation(TableId.class) != null)
                mapValue = field.getAnnotation(TableId.class).value();
            else
                mapValue = field.getName();

            maps.put(field.getName(),tableName+"."+mapValue);
        }
        return maps;
    }

    /**
     * 转换QueryData字段从实体类字段到数据库字段
     */
    public static  <T> QueryData queryDataToData(QueryData queryData, T model){
        //如果查询条件为空，则返回第一页内容
        if (queryData == null){
            queryData = new QueryData();
        }

        //实体类转数据库字段
        Map<String,String> maps = ConvertClassData.classToData(model);
        List<QueryData.SearchData> searchData = queryData.getSData();
        List<QueryData.OrderData> orderData = queryData.getOData();

        if (searchData != null && searchData.size() > 0){
            for (QueryData.SearchData searchDataOne:searchData){
                searchDataOne.setN(maps.get(searchDataOne.getN()));
            }
        }

        if (orderData != null && orderData.size() > 0){
            for (QueryData.OrderData orderDataOne:orderData){
                orderDataOne.setN(maps.get(orderDataOne.getN()));
            }
        }
        return queryData;
    }

    /**
     * 封装一层 queryData字符串转实体 （因为前端需要的查询接口参数必须为param，所以这边需要接受前端传入的string转实体类）
     */
    public static QueryData queryStringToData(String queryDataString){
        QueryData queryData = null;
        if (queryDataString == null || "".equals(queryDataString)){
            queryData = new QueryData();
        }else {
            //json字符串转json对象
            queryData = JSONObject.parseObject(queryDataString,QueryData.class);
        }

        return queryData;

    }


    /**
     * 将vo转QueryData  注意Object为vo， model为实体
     */
    public static <T> QueryData voToQueryData(Object obj,T model){
        //新建查询条件实体类
        QueryData queryData = new QueryData();
        //查询条件(or)
        List<QueryData.SearchData> orDataList = new ArrayList<>();
        //查询条件(add)
        List<QueryData.SearchData> searchDataList = new ArrayList<>();
        //排序条件
        List<QueryData.OrderData> orderDataList = new ArrayList<>();

        //同时获取到父类的字段
        List<Field> fields = new ArrayList<>() ;
        Class temp = obj.getClass();
        while (temp != null){
            fields.addAll(Arrays.asList(temp.getDeclaredFields()));
            temp = temp.getSuperclass();
        }
        //排序字段
        String orderByFild = null;
        //排序类型
        String orderByType = null;

        for (Field field:fields){
            boolean isStatic= Modifier.isStatic(field.getModifiers());
            //排除静态变量
            if (isStatic)
                continue;
            String fiedValue = null;
            try {
                //打开私有访问
                field.setAccessible(true);
                fiedValue = String.valueOf(field.get(obj));
            }catch (Exception e){
                throw new CommonException("参数不合法请检查");
            }
            if ("null".equals(fiedValue))
                continue;

            Annotation queryAnnotation = field.getAnnotation(QueryAnnotation.class);
            if (queryAnnotation != null){
                QueryData.SearchData searchData = new QueryData.SearchData();
                searchData.setN(field.getAnnotation(QueryAnnotation.class).sqlName());
                searchData.setV(fiedValue);
                searchData.setT(field.getAnnotation(QueryAnnotation.class).queryType().getKey());

                //首先判断连接方式
                if ("add".equals(field.getAnnotation(QueryAnnotation.class).connectType())){
                    searchDataList.add(searchData);
                }else {
                    orDataList.add(searchData);
                }


            }else {
                //注入分页条件
                if ("no".equals(field.getName())){
                    queryData.setCurrentPage(Integer.parseInt(fiedValue));
                }else if ("limit".equals(field.getName())){
                    queryData.setPageSize(Integer.parseInt(fiedValue));
                }else if ("isPage".equals(field.getName())){
                    queryData.setPageFlag(Integer.parseInt(fiedValue));
                }else if ("orderBy".equals(field.getName()) && fiedValue != null){
                    orderByFild = fiedValue;
                }else if ("isAsc".equals(field.getName()) && fiedValue != null){
                    orderByType = "false".equals(fiedValue) ? "down":"up";
                }

            }
        }
        queryData.setSData(searchDataList);
        queryData.setOrData(orDataList);

        //注入排序条件
        if (orderByFild != null){
            Map<String,String> maps = ConvertClassData.classToData(model);
            QueryData.OrderData orderData = new QueryData.OrderData();
            orderData.setN(maps.get(orderByFild));
            orderData.setT(orderByType);
            orderDataList.add(orderData);
            queryData.setOData(orderDataList);
        }

        return queryData;

    }
}
