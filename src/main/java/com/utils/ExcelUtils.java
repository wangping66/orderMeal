package com.utils;

import com.constant.HttpResponseCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * excel工具类
 **/
@NoArgsConstructor
@Slf4j
public class ExcelUtils {

   public static final String USER_AGENT = "User-Agent";

    public static final String FIRE_FOX = "firefox";

    public static CellStyle getDefaultBodyStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        return style;
    }


    public static CellStyle getDefaultDatetimeStyle(Workbook wb) {
        CreationHelper createHelper = wb.getCreationHelper();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(HttpResponseCode.FORMAT_DATETIME_DEFAULT));
        return cellStyle;
    }

    public static void export (HttpServletRequest request, HttpServletResponse response, byte[] output, String title) {
        BufferedOutputStream bos = null;
        try {
            // firefox浏览器
            if (request.getHeader(USER_AGENT).toLowerCase().indexOf(FIRE_FOX) > 0) {
                title = new String(title.getBytes("UTF-8"), "ISO8859-1");
            } else {
                // 其他浏览器包括IE浏览器和google浏览器
                title = URLEncoder.encode(title, "UTF-8");
            }
            response.setHeader("conent-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + title);
            bos = new BufferedOutputStream(response.getOutputStream());
            bos.write(output);
            bos.close();
        } catch (IOException e) {
            log.error("ExcelUtils.down exception:{}", e.getMessage());
        }finally {
            if(null != bos){
                try {
                    bos.close();
                } catch (IOException e) {
                    log.error("ExcelUtils.down exception:{}", e.getMessage());
                }
            }
        }

    }

}
