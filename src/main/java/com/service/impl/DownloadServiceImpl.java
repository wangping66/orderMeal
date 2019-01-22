package com.service.impl;




import com.service.DownloadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 下载文件方法
 **/
@Service
@Transactional
@Slf4j
public class DownloadServiceImpl implements DownloadService {

    public static final String USER_AGENT = "User-Agent";

    public static final String FIRE_FOX = "firefox";

    public void setBodyCellValue(Row row, int colIndex, Object value, CellStyle textStyle, CellStyle dateStyle) {
        Cell cell = row.getCell(colIndex) == null ? row.createCell(colIndex) : row.getCell(colIndex);
        CellStyle newTextStyle = null == textStyle ? cell.getCellStyle() : textStyle;
        CellStyle newDateStyle = null == dateStyle ? cell.getCellStyle() : dateStyle;
        if (value == null) {
            cell.setCellValue("");
            cell.setCellStyle(newTextStyle);
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
            cell.setCellStyle(newTextStyle);
        } else if (value instanceof Number) {
            cell.setCellValue(String.valueOf(value));
            cell.setCellStyle(newTextStyle);
        }
    }
}
