package com.michaelj.controller;

import com.michaelj.domain.Code;
import com.michaelj.domain.base.Result;
import com.michaelj.domain.dto.GenerationDTO;
import com.michaelj.service.GenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/web/gen")
public class GenerationController {
    @Autowired
    private GenerationService generationService;

    /**
     * 查询所有世代
     * @return
     */
    @GetMapping
    public Result getAllGen() {
        List<GenerationDTO> genDTOList = generationService.getAllGen();
        int code = genDTOList != null ? Code.GET_OK.getCode() : Code.GET_ERR.getCode();
        String msg = genDTOList != null ? "查询成功" : "数据查询失败， 请重试！";
        return new Result(code, genDTOList, msg);
    }
}
