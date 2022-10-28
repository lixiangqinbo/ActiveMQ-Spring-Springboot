package com.lxsx.easyexcel.dao;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.lxsx.easyexcel.converter.LocalDateTimeConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ContentRowHeight(30)
@HeadRowHeight(40)
@ColumnWidth(25)
public class WriteDemo {
    /**
     * idnex 如果不连续则回空缺列显示 导致第某一列空的
     * @ExcelProperty({"主标题", "字符串标题"}): 可以设置 复杂头写入
     */
    @ExcelProperty(value = "字符串标题",index = 0)
    private String string;

    @ExcelProperty(value = "日期标题",converter = LocalDateTimeConverter.class,index = 1)
    private Date date; //date 类型 还会报错:LocalDateTimeConverter解决
    @ColumnWidth(50)
    @ExcelProperty(value = "数字标题",index = 2)
    private Double doubleData;

    /*忽略这个字段*/
    @ExcelIgnore
    private String ignore;
}