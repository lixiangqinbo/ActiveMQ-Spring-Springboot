package com.lxsx.easyexcel.read;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.lxsx.easyexcel.dao.DemoDAO;
import com.lxsx.easyexcel.enties.DemoData;
import com.lxsx.easyexcel.enties.DemoExtraData;
import com.lxsx.easyexcel.enties.IndexOrNameData;
import com.lxsx.easyexcel.listener.DemoDataListener;
import com.lxsx.easyexcel.listener.NoModelDataListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class EasyexcelReadTests {
    private final Integer ROW_SHEET=0;
    @Test
    public void simpleRead() {
        /*写法一*/
        String fileName =  "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取100条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }
        })).sheet(ROW_SHEET).doRead();

        /*写法二*/
        /*有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去*/
        fileName =  "demo" + File.separator + "demo.xlsx";
        /*这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭*/
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener(new DemoDAO())).sheet(ROW_SHEET).doRead();

    }

    /**
     * 指定列的下标或者列名
     * 1. 创建excel对应的实体对象,并使用@ExcelProperty注解
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，
     * 3. 直接读即可
     */
    @Test
    public void indexOrNameRead() {
        String fileName =  "demo" + File.separator + "demo1.xlsx";
        //这里默认读取第一个sheet
        EasyExcel.read(fileName, IndexOrNameData.class, new PageReadListener<IndexOrNameData>(dataList -> {
            for (IndexOrNameData demoData : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }
        })).sheet(ROW_SHEET).doRead();
    }

    /**
     * 读取全部或者多个sheet
     */
    @Test
    public void repeatedRead() {
        String fileName ="demo" + File.separator + "demo.xlsx";
        /**
         * 读取全部sheet
         * 这里需要注意 DemoDataListener的doAfterAllAnalysed
         * 会在每个sheet读取完毕后调用一次。然后所有sheet都会往同一个DemoDataListener里面写
         */
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener(new DemoDAO())).doReadAll();

        // 读取部分sheet
        try (ExcelReader excelReader = EasyExcel.read(fileName).build()) {
            // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
            ReadSheet readSheet1 =
                    EasyExcel.readSheet(0).head(DemoData.class).registerReadListener(new DemoDataListener(new DemoDAO())).build();
            ReadSheet readSheet2 =
                    EasyExcel.readSheet(1).head(DemoData.class).registerReadListener(new DemoDataListener(new DemoDAO())).build();
            // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
            excelReader.read(readSheet1, readSheet2);
        }
    }

    /**
     * 日期、数字或者自定义格式转换
     * 默认读的转换器{@DefaultConverterLoader，@loadDefaultReadConverter()}
     * 1. 创建excel对应的实体对象
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器
     * 3. 直接读即可
     */
    @Test
    public void converterRead() {
        String fileName = "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        EasyExcel.read(fileName, IndexOrNameData.class, new DemoDataListener(new DemoDAO()))
                // 这里注意 我们也可以registerConverter来指定自定义转换器， 但是这个转换变成全局了， 所有java为string,excel为string的都会用这个转换器。
                // 如果就想单个字段使用请使用@ExcelProperty 指定converter
                // .registerConverter(new CustomStringStringConverter())
                // 读取sheet
                .sheet()
                //这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，因为默认会根据IndexOrNameData 来解析，他没有指定头，也就是默认1行
                .headRowNumber(1)
                .doRead();
    }

    /**
     * 同步的返回，不推荐使用，如果数据量大会把数据放到内存里面
     */
    @Test
    public void synchronousRead() {
        String fileName = "demo" + File.separator + "demo.xlsx";
        /*这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish*/
        List<DemoData> list = EasyExcel.read(fileName).head(DemoData.class).sheet().doReadSync();
        for (DemoData data : list) {
            log.info("读取到数据:{}", JSON.toJSONString(data));
        }
        /*这里 也可以不指定class，返回一个list，然后读取第一个sheet 同步读取会自动finish*/
        List<Map<Integer, String>> listMap = EasyExcel.read(fileName).sheet().doReadSync();
        for (Map<Integer, String> data : listMap) {
            /*返回每条数据的键值对 表示所在的列 和所在列的值*/
            log.info("读取到数据:{}", JSON.toJSONString(data));
        }
    }

    /**
     * 读取表头数据
     * 1. 创建excel对应的实体对象
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器 @DemoDataListener
     * 3. 直接读即可
     */
    @Test
    public void headerRead() {
        String fileName =  "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener(new DemoDAO())).sheet().doRead();
    }

    /**
     * 额外信息（批注、超链接、合并单元格信息读取）
     * 由于是流式读取，没法在读取到单元格数据的时候直接读取到额外信息，所以只能最后通知哪些单元格有哪些额外信息
     * 1. 创建excel对应的实体对象 DemoExtraData
     * 2. 由于默认异步读取excel，所以需要创建excel一行一行的回调监听器
     * 3. 直接读即可
     * @since 2.2.0-beat1
     */
    @Test
    public void extraRead() {
        String fileName = "demo" + File.separator + "extra.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        EasyExcel.read(fileName, DemoExtraData.class, new DemoDataListener(new DemoDAO()))
                // 需要读取批注 默认不读取
                .extraRead(CellExtraTypeEnum.COMMENT)
                // 需要读取超链接 默认不读取
                .extraRead(CellExtraTypeEnum.HYPERLINK)
                // 需要读取合并单元格信息 默认不读取
                .extraRead(CellExtraTypeEnum.MERGE).sheet().doRead();
    }


    /**
     * 不创建对象的读
     */
    @Test
    public void noModelRead() {
        String fileName = "demo" + File.separator + "demo.xlsx";
        // 这里 只要，然后读取第一个sheet 同步读取会自动finish
        EasyExcel.read(fileName, new NoModelDataListener()).sheet().doRead();
    }

}
