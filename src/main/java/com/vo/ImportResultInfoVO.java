package com.vo;
import lombok.Data;

/**
 * 导入EXCEL 结果返回的信息
 *

 **/
@Data
public class ImportResultInfoVO {
    private String rowNum;

    private String detailInfo;

    public ImportResultInfoVO() {
    }

    public ImportResultInfoVO(String rowNum, String detailInfo) {
        this.rowNum = rowNum;
        this.detailInfo = detailInfo;
    }
}
