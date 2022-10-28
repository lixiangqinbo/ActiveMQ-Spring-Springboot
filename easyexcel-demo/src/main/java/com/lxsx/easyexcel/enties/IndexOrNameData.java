package com.lxsx.easyexcel.enties;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.lxsx.easyexcel.converter.CustomStringStringConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class IndexOrNameData {


    /*强制读取第三个 这里不建议 index 和 name 同时用，要么一个对象只用index，要么一个对象只用name去匹配*/
    @ExcelProperty(value = "双精度")
    private Double doubleData;

    /*用名字去匹配，这里需要注意，如果名字重复，会导致只有一个字段读取到数据*/
    @ExcelProperty(value = "字符串标题")

    private String string;
    @ExcelProperty(value = "日期标题")
    private Date date;

    @ExcelProperty(converter = CustomStringStringConverter.class)//我自定义 转换器，不管数据库传过来什么 。我给他加上“自定义：”
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")//这里用string 去接日期才能格式化。我想接收年月日格式
    @NumberFormat(style = NumberFormat.Style.NUMBER )//我想接收百分比的数字 也可以 pattern ="正则"
    private String testData;
}