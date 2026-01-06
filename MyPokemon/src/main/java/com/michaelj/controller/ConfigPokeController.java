package com.michaelj.controller;

import com.michaelj.application.ConfigPokeApplicationService;
import com.michaelj.domain.Code;
import com.michaelj.domain.base.Result;
import com.michaelj.domain.dto.ConfigPokeDTO;
import com.michaelj.domain.dto.ValidateConfig;
import com.michaelj.service.ConfigPokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/web/config")
public class ConfigPokeController {
    @Autowired
    private ConfigPokeApplicationService configPokeApplicationService;
    @Autowired
    private ConfigPokeService configPokeService;



    /**
     * 新增
     */
    @PostMapping("/add")
    public Result save(@Validated({ValidateConfig.add.class}) @RequestBody ConfigPokeDTO configPokeDTO) {
        boolean flag = configPokeApplicationService.save(configPokeDTO);
        return new Result(flag ? Code.SAVE_OK.getCode() : Code.SAVE_ERR.getCode(), flag);
    }


    /**
     * 编辑
     */
    @PostMapping("/update")
    public Result update(@Validated({ValidateConfig.update.class}) @RequestBody ConfigPokeDTO configPokeDTO) {
        boolean flag = configPokeApplicationService.update(configPokeDTO);
        return new Result(flag ? Code.SAVE_OK.getCode() : Code.SAVE_ERR.getCode(), flag);
    }


    /**
     * 删除
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody ConfigPokeDTO configPokeDTO) {
        Long id = configPokeDTO.getId();
        boolean flag = configPokeApplicationService.delete(id);
        return new Result(flag ? Code.SAVE_OK.getCode() : Code.SAVE_ERR.getCode(), flag);
    }



}
