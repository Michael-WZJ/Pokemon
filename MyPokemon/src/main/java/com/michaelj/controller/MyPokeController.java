package com.michaelj.controller;

import com.michaelj.domain.Code;
import com.michaelj.domain.entity.PokemonBaseInfo;
import com.michaelj.domain.base.Result;
import com.michaelj.domain.query.PokeBaseInfoQuery;
import com.michaelj.service.PokemonBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/mypokes")
public class MyPokeController {
    @Autowired
    private PokemonBaseInfoService baseInfoService;

    /**
     * 条件查询宝可梦信息
     * @param pokeBaseInfoQuery
     * @return
     */
    @PostMapping("/pageList")
    public Result pageList(@RequestBody PokeBaseInfoQuery pokeBaseInfoQuery) {
        List<PokemonBaseInfo> pokemonList = baseInfoService.selectPageList(pokeBaseInfoQuery);
        return new Result(Code.GET_OK.getCode(), pokemonList);
    }

    @GetMapping
    public Result getAllBaseInfo() {
        //System.out.println("controller get");

        List<PokemonBaseInfo> pokemonList = baseInfoService.getAll();
        int code = pokemonList != null ? Code.GET_OK.getCode() : Code.GET_ERR.getCode();
        String msg = pokemonList != null ? "查询成功" : "数据查询失败， 请重试！";
        return new Result(code, pokemonList, msg);
    }

    @GetMapping("/{pokeCode}")
    public Result getByCode(@PathVariable String pokeCode) {
        PokemonBaseInfo pokemon = baseInfoService.getByCode(pokeCode);
        int code = pokemon != null ? Code.GET_OK.getCode() : Code.GET_ERR.getCode();
        String msg = pokemon != null ? "查询成功" : "数据查询失败， 请重试！";
        return new Result(code, pokemon, msg);
    }

    @GetMapping("/conditions")
    public Result getByCondition(@RequestBody PokemonBaseInfo pokemon) {
        List<PokemonBaseInfo> pokemonList = baseInfoService.getByCondition(pokemon);
        int code = pokemonList != null ? Code.GET_OK.getCode() : Code.GET_ERR.getCode();
        String msg = pokemonList != null ? "查询成功" : "数据查询失败， 请重试！";
        return new Result(code, pokemonList, msg);
    }

    /**
     * 获取所有宝可梦数量
     * @return
     */
    @GetMapping("/count")
    public Result getPokeCount() {
        Long cnt = baseInfoService.getPokeCount();
        int code = cnt != null ? Code.GET_OK.getCode() : Code.GET_ERR.getCode();
        String msg = cnt != null ? "查询成功" : "数据查询失败， 请重试！";
        return new Result(code, cnt, msg);
    }

    @PostMapping
    public Result save(@RequestBody PokemonBaseInfo pokemon) {
        boolean flag = baseInfoService.save(pokemon);
        return new Result(flag ? Code.SAVE_OK.getCode() : Code.SAVE_ERR.getCode(), flag);
    }

    @PutMapping
    public Result update(@RequestBody PokemonBaseInfo pokemon) {
        boolean flag = baseInfoService.update(pokemon);
        return new Result(flag ? Code.UPDATE_OK.getCode() : Code.UPDATE_ERR.getCode(), flag);
    }

    @DeleteMapping("/{pokeCode}")
    public Result deleteByCode(@PathVariable String pokeCode) {
        boolean flag = baseInfoService.deleteByCode(pokeCode);
        return new Result(flag ? Code.DELETE_OK.getCode() : Code.DELETE_ERR.getCode(), flag);
    }
}
