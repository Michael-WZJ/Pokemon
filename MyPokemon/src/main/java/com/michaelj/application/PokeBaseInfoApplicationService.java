package com.michaelj.application;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.michaelj.domain.dto.PokemonBaseInfoDTO;
import com.michaelj.domain.entity.PokemonBaseInfo;
import com.michaelj.domain.excel.PokemonBaseInfoImportExcel;
import com.michaelj.domain.query.PokeBaseInfoQuery;
import com.michaelj.infrastructure.constant.PokeExceptionEnum;
import com.michaelj.infrastructure.exception.BusinessException;
import com.michaelj.infrastructure.utils.DateUtils;
import com.michaelj.infrastructure.utils.EasyExcelUtils;
import com.michaelj.infrastructure.utils.excel.ExcelListener;
import com.michaelj.service.EvolutionService;
import com.michaelj.service.PokemonBaseInfoService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PokeBaseInfoApplicationService {
    @Autowired
    private PokemonBaseInfoService baseInfoService;

    @Autowired
    private EvolutionService evolutionService;

    @Autowired
    private EvolutionApplicationService evolutionApplicationService;

    /**
     * 导出模版
     */
    public static final String EXCEL_EXPORT_NAME = "宝可梦基础信息";

    /**
     * 按code查询
     * @param code
     * @return
     */
    public PokemonBaseInfoDTO getByCode(String code) {
        PokemonBaseInfoDTO baseInfoDTO = baseInfoService.getByCode(code);
        // 获取进化编号
        baseInfoDTO.setEvolution(evolutionService.getEvolCodesStr(code));
        // 获取进化前编号
        baseInfoDTO.setFilial(evolutionService.getFilialCode(code));
        // 获取下一个编号
        baseInfoDTO.setNextCode(baseInfoService.getNextCode(code));
        // 获取上一个编号
        baseInfoDTO.setPrevCode(baseInfoService.getPrevCode(code));
        return baseInfoDTO;
    }

    /**
     * 根据 编号列表 查询宝可梦
     * @param codeList
     * @return
     */
    public List<PokemonBaseInfoDTO> getByCodeList(List<String> codeList) {
        return baseInfoService.getByCodeList(codeList);
    }

    /**
     * 新增宝可梦基本信息
     * @param pokemon
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean save(PokemonBaseInfoDTO pokemon) {
        boolean flag = baseInfoService.save(pokemon);
        if (!flag) {
            return false;
        }

        if (StrUtil.isBlank(pokemon.getEvolution())) {
            // 如果 进化 为空，则不需要添加进化链
            return true;
        }

        // 如果 进化 不为空，则添加进化链
        baseInfoService.removeDuplicatedEvols(pokemon); // 去重
        return evolutionService.saveByPoke(pokemon);
    }

    /**
     * 修改宝可梦基本信息
     * @param pokemon
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean update(PokemonBaseInfoDTO pokemon) {
        boolean flag = baseInfoService.update(pokemon);
        if (!flag) {
            return false;
        }

        baseInfoService.removeDuplicatedEvols(pokemon); // 去重
        return evolutionApplicationService.updateByPokeWithRules(pokemon);
    }

    /**
     * 删除宝可梦基本信息
     * @param code
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByCode(String code) {
        boolean flag = baseInfoService.deleteByCode(code);
        if (!flag) {
            return false;
        }

        // todo 直接删除，不需要判断返回值；或者在application层判断 不存在则不删除
        // 同时删除进化关系
        evolutionService.deleteByFilialCode(code);
        return true;
    }

    public void exportData(PokeBaseInfoQuery query, HttpServletResponse response) {
        // todo 导出数量限制？
        List<PokemonBaseInfo> baseInfoList = baseInfoService.getByCondition(query);
        if (CollUtil.isEmpty(baseInfoList)) {
            log.warn("【WARN 导出数据为空】 未查询到数据");
            return;
        }

        AtomicInteger index = new AtomicInteger(1);
        List<PokemonBaseInfoImportExcel> excelList = baseInfoList.stream()
                .map(pokemonBaseInfo -> {
                    PokemonBaseInfoImportExcel excel = new PokemonBaseInfoImportExcel();
                    BeanUtils.copyProperties(pokemonBaseInfo, excel);
                    excel.setIndex(String.valueOf(index.getAndIncrement()));
                    return excel;
                })
                .collect(Collectors.toList());

        String fileName = EXCEL_EXPORT_NAME + DateUtils.format(LocalDateTime.now(), DateUtils.MINUTE_PATTERN);

        try {
            EasyExcelUtils.exportExcel(response, fileName, EasyExcelUtils.XLSX, excelList, PokemonBaseInfoImportExcel.class);
        } catch (Exception e) {
            log.error("【ERROR 导出Excel错误】 exception: {}", e.getMessage());
            throw new BusinessException(PokeExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }

    public void importExcel(MultipartFile file) {
        ExcelListener<PokemonBaseInfoImportExcel> listener = new ExcelListener<>();
        List<PokemonBaseInfoImportExcel> excelList = EasyExcelUtils.readFile(file, PokemonBaseInfoImportExcel.class, listener);
        return;
    }
}
