package com.michaelj.infrastructure.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.michaelj.infrastructure.exception.BusinessException;
import com.michaelj.infrastructure.constant.PokeExceptionEnum;
import com.michaelj.infrastructure.utils.excel.CustomCellWriteHandler;
import com.michaelj.infrastructure.utils.excel.ExcelListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class EasyExcelUtils {
    public static final String XLSX = ".xlsx";
    public static final String XLS = ".xls";
    public static final String CSV = ".csv";

    public static final String ENCODE = "UTF-8";


    public static <T> void exportExcel(HttpServletResponse response, String fileName, String fileType, List<T> data, Class<?> clazz) throws IOException {
        try {
            ServletOutputStream outputStream = EasyExcelUtils.getOutputStreamWithType(fileName, fileType, response);
            EasyExcel.write(outputStream, clazz)
                    .registerWriteHandler(getHorizontalCellStyleStrategy())
                    .registerWriteHandler(new CustomCellWriteHandler())
                    .sheet(fileName)
                    .doWrite(data);
        } catch (IOException e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<>();
            map.put("status", "failure");
            map.put("message", "download:" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

    /**
     * 根据文件类型 设置 HTTP响应头
     * @param fileName
     * @param fileType
     * @param response
     * @return
     * @throws IOException
     */
    public static ServletOutputStream getOutputStreamWithType(String fileName, String fileType, HttpServletResponse response) throws IOException {
        fileName = URLEncoder.encode(fileName, ENCODE).replaceAll("\\+", "%20");
        if (StrUtil.equals(ExcelTypeEnum.XLSX.getValue(), fileType)) {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        } else if (StrUtil.equals(ExcelTypeEnum.XLS.getValue(), fileType)) {
            response.setContentType("application/vnd.ms-excel");
        } else if (StrUtil.equals(CSV, fileType)) {
            response.setContentType("text/csv");
        }

        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition",
                "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + fileType);
        return response.getOutputStream();
    }


    public static <T> List<T> readFile(MultipartFile file, Class<?> clazz, ExcelListener<T> listener) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            log.error("【ERROR 解析错误】 Excel文件解析错误：{}", e.getMessage());
        }
        if (inputStream == null) {
            throw new BusinessException(PokeExceptionEnum.EXCEL_PARSE_FAIL);
        }

        // 读取第一个sheet 文件流会自动关闭
        EasyExcel.read(inputStream, clazz, listener).sheet().doRead();
        return listener.getObjects();
    }


    public static HorizontalCellStyleStrategy getHorizontalCellStyleStrategy() {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }



}
