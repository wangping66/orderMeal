package com.utils;

import com.baomidou.mybatisplus.annotations.TableField;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * Title: cloud-server--com.facemeng.sun.common.utils.CommonUtil
 * Description:
 * Copyright: Copyright (c) 2018/01
 * Create DateTime: 2018/1/11 上午10:23
 *
 * @author suanmilk
 * @version v2
 */
public class CommonUtil {

    public static Map<String, String> entityResultMap(Map<String, String> mapping, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 过滤静态属性（serialVersionUID）
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            if (isStatic)
                continue;
            TableField annotation = field.getAnnotation(TableField.class);
            if (annotation != null) {
                mapping.put(field.getName(), annotation.value());
            } else {
                mapping.put(field.getName(), field.getName());
            }
        }
        if (clazz.getSuperclass() == null) {
            return mapping;
        }
        //不断调用getEntityTableFields(),知道没有父类为止
        entityResultMap(mapping, clazz.getSuperclass());
        return mapping;
    }


    /*//判断客户是否拥有某种通知权限
    public static Boolean isHaveNoticeWay(String noticeWay,String detailsWay){
        if (StringUtils.isEmpty(noticeWay))
            return false;
        String[] detailsWayList = noticeWay.split(",");
        return Arrays.asList(detailsWayList).contains(detailsWay);
    }

    //整理提示语，根据业务判断 考勤 or
    public static String arrangPushContent(String cameraName, Date occurTime, String personnelName, NoticeFunctionTypeEnum noticeFunctionTypeEnum){
        //整理提示语，根据业务判断 考勤 or 警报
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String occurTimeStr = formatter.format(occurTime);

        String msgContent = "";
        if (NoticeFunctionTypeEnum.ALERT.equals(noticeFunctionTypeEnum)){
            //警报
            msgContent = personnelName+"于"+occurTimeStr+"在"+cameraName+"，请注意！";
        }else if (NoticeFunctionTypeEnum.CAMERA_ERROR.equals(noticeFunctionTypeEnum)){
            //设备异常
            msgContent = cameraName+"于"+occurTimeStr+"出现设备异常，请注意！";
        }

        return msgContent;

    }*/
}
