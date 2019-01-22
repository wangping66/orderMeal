package com.service;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;



/**
 * 下载文件service
 **/
public interface DownloadService {

     void setBodyCellValue(Row row, int colIndex, Object value, CellStyle textStyle, CellStyle dateStyle);
}
