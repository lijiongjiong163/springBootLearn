package com.ljj.hello.service;

import com.ljj.hello.domain.AjaxResponse;
import com.ljj.hello.domain.ExcelResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 写一个转换器，将返回结果转换成excel返回给前台
 * Converter：转换器
 * 想要实现将response或者request中的信息转换成别的数据就需要继承转换器来实现AbstractHttpMessageConverter
 */
@Component
public class ResponseToXlsConverter extends AbstractHttpMessageConverter<ExcelResponse> {

    private static final MediaType EXCEL_TYPE = MediaType.valueOf("application/vnd.ms-excel");

    /**
     * 告诉前端的响应的格式是excel格式
     */
    public ResponseToXlsConverter() {
        super(EXCEL_TYPE);
    }

    /**
     *
     * @param aClass 传进来一个对象，用这个对象做判断
     * @return true则执行这个转换器的方法，false则不进行转换
     */
    @Override
    protected boolean supports(Class<?> aClass) {
        return ExcelResponse.class==aClass;
    }

    /**
     * 读的时候进行转换，对应着前台传过来的requestBody
     * @param aClass
     * @param httpInputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected ExcelResponse readInternal(Class<? extends ExcelResponse> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    /**
     * 写的时候进行转换，对应着后台传到前台的responseBody
     * @param excelResponse
     * @param httpOutputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(ExcelResponse excelResponse, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        final Workbook workbook = new HSSFWorkbook();
        final Sheet sheet = workbook.createSheet();
        final Row row = sheet.createRow(0);
        row.createCell(0).setCellValue(excelResponse.getMessage());
        row.createCell(1).setCellValue(excelResponse.getData().toString());
        workbook.write(httpOutputMessage.getBody());

    }
}
