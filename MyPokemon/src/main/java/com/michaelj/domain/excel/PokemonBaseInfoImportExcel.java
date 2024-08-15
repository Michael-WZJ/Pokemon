package com.michaelj.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonBaseInfoImportExcel {
    /**
     * 序号
     */
    @ExcelProperty(value = "序号", index = 0)
    private String index;

    /**
     * 编号
     */
    @ExcelProperty(value = "编号", order = 1)
    private String pokeBaseCode;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称", order = 2)
    private String pokeBaseName;

    /**
     * 世代
     */
    @ExcelProperty(value = "世代", order = 3)
    private String gen;

    /**
     * 属性1
     */
    @ExcelProperty(value = "属性1", order = 4)
    private String prop1;

    /**
     * 属性2
     */
    @ExcelProperty(value = "属性2", order = 5)
    private String prop2;

    /**
     * 日文名
     */
    @ExcelProperty(value = "日文名", order = 6)
    private String nameJpn;

    /**
     * 英文名
     */
    @ExcelProperty(value = "英文名", order = 7)
    private String nameEng;
}
