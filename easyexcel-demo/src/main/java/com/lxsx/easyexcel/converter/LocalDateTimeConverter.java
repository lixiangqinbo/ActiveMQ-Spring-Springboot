package com.lxsx.easyexcel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author xBaozi
 * @version 1.0
 * @classname LocalDateTimeConverter
 * @description EasyExcel LocalDateTime转换器
 * @date 2022/3/19 2:17
 */

public class LocalDateTimeConverter implements Converter<Date> {

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Class<LocalDateTime> supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 这里将将 读的时候将String 转 date
     * @param cellData
     * @param contentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public Date convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
                                           GlobalConfiguration globalConfiguration) throws Exception {

        return new SimpleDateFormat(DEFAULT_PATTERN).parse(cellData.getStringValue());
    }

    /**
     * 写的时候可以将自己的 Date 转成 String
     * @param value
     * @param contentProperty
     * @param globalConfiguration
     * @return
     */
    @Override
    public WriteCellData<?> convertToExcelData(Date value, ExcelContentProperty contentProperty,
                                               GlobalConfiguration globalConfiguration) {
        return new WriteCellData<>(new SimpleDateFormat(DEFAULT_PATTERN).format(value));
    }
}

