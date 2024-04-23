package com.michaelj.controller;

import com.michaelj.domain.Code;
import com.michaelj.domain.ValidList;
import com.michaelj.domain.base.Result;
import com.michaelj.domain.dto.PropertyDTO;
import com.michaelj.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/prop")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    /**
     * 查询所有属性
     * @return
     */
    @GetMapping
    public Result getAllProp() {
        List<PropertyDTO> propDTOList = propertyService.getAllProp();
        int code = propDTOList != null ? Code.GET_OK.getCode() : Code.GET_ERR.getCode();
        String msg = propDTOList != null ? "查询成功" : "数据查询失败， 请重试！";
        return new Result(code, propDTOList, msg);
    }

    /**
     * 批量更新 属性 css颜色
     * @param propertyDTOList
     * @return
     */
    @PutMapping("/batch/color")
    public Result batchUpdate(@Validated @RequestBody ValidList<PropertyDTO> propertyDTOList) {
        int numToUpdate = propertyDTOList.size();
        int numUpdated = propertyService.batchUpdateProps(propertyDTOList.getList());
        String result = String.format("成功修改了 %s (%s) 条属性", numUpdated, numToUpdate);
        return new Result(Code.UPDATE_OK.getCode(), result);
    }
}
