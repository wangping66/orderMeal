package com.vo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 导入excel结果Vo
 */
@Data
@Accessors(chain = true)
public class ImportExcelResultVO {

    /**
     * 导入数据数量
     */
    private int total;

    /**
     * 失败数量
     */

    private int errorTotal;

    /**
     * 失败数据具体信息
     */

    private List<ImportResultInfoVO> errorInfo;

    private boolean resultFlag = false;

    public ImportExcelResultVO() {
    }
    public ImportExcelResultVO(boolean resultFlag ) {
        this.resultFlag = resultFlag;
    }

    public ImportExcelResultVO(int total, int errorTotal, List<ImportResultInfoVO> errorInfo) {
        this.total = total;
        this.errorTotal = errorTotal;
        this.errorInfo = errorInfo;
    }

    public ImportExcelResultVO(boolean resultFlag,int total, int errorTotal, List<ImportResultInfoVO> errorInfo) {
        this.resultFlag = resultFlag;
        this.total = total;
        this.errorTotal = errorTotal;
        this.errorInfo = errorInfo;
    }
}
