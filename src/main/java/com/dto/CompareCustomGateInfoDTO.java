package com.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author: zhuxiang
 * @date: 2018/12/6 11:02
 * @description: 接受数据-自定义比对门禁信息实体类
 */
@Data
@ToString(exclude = ("faceImageBase64"))
public class CompareCustomGateInfoDTO {
    /**
     * 抓拍人脸图片BASE64
     */
    private String faceImageBase64;
    private Date snapTime;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 人员id
     */
    private Long personId;

}
