package com.lxsx.easyexcel.enties;

import com.alibaba.excel.annotation.ExcelProperty;
import com.lxsx.easyexcel.converter.LocalDateTimeConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
public class DemoData {

    @ExcelProperty(value = "字符串")
    private String string;

    @ExcelProperty(value = "时间",converter = LocalDateTimeConverter.class)
    private Date date;

    @ExcelProperty(value = "双精度")
    private Double doubleData;
}