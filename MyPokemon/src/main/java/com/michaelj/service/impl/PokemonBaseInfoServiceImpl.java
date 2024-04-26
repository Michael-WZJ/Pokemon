package com.michaelj.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.michaelj.dao.PokemonBaseInfoDao;
import com.michaelj.domain.Code;
import com.michaelj.domain.base.Page;
import com.michaelj.domain.converter.PokemonBaseInfoConverter;
import com.michaelj.domain.dto.PokemonBaseInfoDTO;
import com.michaelj.domain.entity.PokemonBaseInfo;
import com.michaelj.domain.query.PokeBaseInfoQuery;
import com.michaelj.infrastructure.constant.BaseConst;
import com.michaelj.infrastructure.constant.PokeExceptionEnum;
import com.michaelj.infrastructure.exception.BusinessException;
import com.michaelj.infrastructure.utils.BaseUtils;
import com.michaelj.infrastructure.utils.PageExecutor;
import com.michaelj.service.PokemonBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO 定义 Service bean
@Service
public class PokemonBaseInfoServiceImpl implements PokemonBaseInfoService {
    public static final String SPLIT = "-";
    public static final String DEFAULT_SUFFIX = "00";
    public static final int FIRST = 0;
    public static final int SECOND = 1;

    @Autowired
    private PokemonBaseInfoDao baseInfoDao;

    @Autowired
    private PokemonBaseInfoConverter baseInfoConverter;

    /**
     * 条件查询宝可梦信息
     *
     * @param query
     * @return
     */
    @Override
    public Page<PokemonBaseInfoDTO> selectPageList(PokeBaseInfoQuery query) {
        return PageExecutor.pagination(query, new PageExecutor.ConvertPageDataLoader<PokemonBaseInfo, PokemonBaseInfoDTO>() {
            @Override
            public List<PokemonBaseInfo> load() {
                return baseInfoDao.selectPageList(query);
            }

            @Override
            public long count() {
                return baseInfoDao.selectPageListCount(query);
            }

            @Override
            public PokemonBaseInfoDTO convert(PokemonBaseInfo entity) {
                return baseInfoConverter.toDto(entity);
            }
        });
    }

    @Override
    public List<PokemonBaseInfo> getAll() {
        return baseInfoDao.getAll();
    }

    @Override
    public PokemonBaseInfoDTO getByCode(String code) {
        return baseInfoConverter.toDto(baseInfoDao.getByCode(code));
    }

    @Override
    public List<PokemonBaseInfo> getByCondition(PokemonBaseInfo pokemon) {
        return baseInfoDao.getByCondition(pokemon);
    }

    @Override
    public Long getPokeCount() {
        return baseInfoDao.getPokeCount();
    }

    /**
     * 根据编号 获取下一个编号
     *
     * @param code
     * @return
     */
    @Override
    public String getNextCode(String code) {
        String validCode;
        // 1、是否是带-的编号+1
        validCode = generateNextCode(code, true);
        if (!ObjectUtil.isEmpty(getByCode(validCode))) {
            return validCode;
        }
        // 2、是否是直接编号+1
        validCode = generateNextCode(code, false);
        if (!ObjectUtil.isEmpty(getByCode(validCode))) {
            return validCode;
        }
        // 3、都不是，则代表是最后一个编号，返回第一个编号
        return BaseConst.FIRST_CODE;
    }

    /**
     * 根据编号 获取上一个编号
     *
     * @param code
     * @return
     */
    @Override
    public String getPrevCode(String code) {
        if (BaseConst.FIRST_CODE.equals(code)) {
            // 处理 0001, 分页查最后一个
            long cnt = getPokeCount();
            PokeBaseInfoQuery query = new PokeBaseInfoQuery();
            query.setStart(cnt - 1);
            query.setPageSize(1);
            List<PokemonBaseInfo> result = baseInfoDao.selectPageList(query);
            return result.get(BaseConst.FIRST_ITEM).getPokeBaseCode();
        }

        String[] arr = code.split(SPLIT);
        int len = arr.length;
        String codePrefix = arr[FIRST];
        String codeSuffix;

        if (len > 1) {
            // 当前编号带后缀
            codeSuffix = BaseUtils.computeCode(arr[SECOND], false);
            return DEFAULT_SUFFIX.equals(codeSuffix) ? codePrefix : String.join(SPLIT, codePrefix, codeSuffix);
        } else {
            // 当前编号不带后缀
            codePrefix = BaseUtils.computeCode(codePrefix, false);
            // 查询以前缀开头的数量
            int cnt = baseInfoDao.selectByCodePrefix(codePrefix);
            if (cnt == 0) {
                // 编号不连续，采用返回第一个宝可梦作为临时方案
                return BaseConst.FIRST_CODE;
            }
            if (cnt == 1) {
                return codePrefix;
            } else {
                codeSuffix = BaseUtils.buildCode(cnt-1, 2);
                return String.join(SPLIT, codePrefix, codeSuffix);
            }
        }
    }

    @Override
    public boolean save(PokemonBaseInfoDTO pokemon) {
        try {
            verifyAddBaseInfo(pokemon);
            int flag = baseInfoDao.save(baseInfoConverter.toEntity(pokemon));
            // 获取插入数据后的自增ID
//            Long id = pokemon.getPokeBaseId(); // sqlite获取不到，可能要加方言？
//            System.out.println("自增ID" + id);
            return flag > 0;
        } catch (DataAccessException e) {
            //TODO 如何处理SQL异常
            throw new BusinessException(Code.PROJECT_BUSINESS_ERR, "SQL异常 ：   " + e.getMessage(), e);
            // springboot包装了所有DB的异常
            // https://www.codenong.com/cs106502042/
        }
    }

    /**
     * 修改宝可梦基本信息
     * @param pokemon
     * @return
     */
    @Override
    public boolean update(PokemonBaseInfoDTO pokemon) {
        verifyUpdateBaseInfo(pokemon);
        int flag = baseInfoDao.update(baseInfoConverter.toEntity(pokemon));
        return flag > 0;
    }

    /**
     * 删除宝可梦基本信息
     * @param code
     * @return
     */
    @Override
    public boolean deleteByCode(String code) {
        int flag = baseInfoDao.deleteByCode(code);
        return flag > 0;
    }

    /**
     * 批量删除宝可梦基本信息
     *
     * @param codeList
     * @return
     */
    @Override
    public int deleteByCodeList(List<String> codeList) {
        return baseInfoDao.deleteByCodeList(codeList);
    }

    public void verifyUpdateBaseInfo(PokemonBaseInfoDTO baseInfoDTO) {
        generalVerify(baseInfoDTO);
        // code不应被修改
        String code = baseInfoDTO.getPokeBaseCode();
        String newName = baseInfoDTO.getPokeBaseName();
        Long id = baseInfoDTO.getPokeBaseId();

        // 判断参数是否齐全 -- 注解

        // 判断 该编号是否存在 或者 编号与id是否能对应上
        PokemonBaseInfo poke = baseInfoDao.getByCode(code);
        if (ObjectUtil.isEmpty(poke) || !id.equals(poke.getPokeBaseId())) {
            throw new BusinessException(PokeExceptionEnum.CODE_REPEAT_FAIL);
        }
        // 判断名称是否重复
        poke = baseInfoDao.selectByName(newName);
        if (!ObjectUtil.isEmpty(poke) && !code.equals(poke.getPokeBaseCode())) {
            throw new BusinessException(PokeExceptionEnum.NAME_REPEAT_FAIL);
        }
    }

    public void verifyAddBaseInfo(PokemonBaseInfoDTO baseInfoDTO) {
        generalVerify(baseInfoDTO);
        String code = baseInfoDTO.getPokeBaseCode();
        String name = baseInfoDTO.getPokeBaseName();

        // 判断参数是否齐全 -- 注解

        // 判断编号是否重复
        if (!ObjectUtil.isEmpty(baseInfoDao.getByCode(code))) {
            throw new BusinessException(PokeExceptionEnum.CODE_REPEAT_FAIL);
        }
        // 判断名称是否重复
        if (!ObjectUtil.isEmpty(baseInfoDao.selectByName(name))) {
            throw new BusinessException(PokeExceptionEnum.NAME_REPEAT_FAIL);
        }
    }

    public void generalVerify(PokemonBaseInfoDTO baseInfoDTO) {

    }

    @Override
    public String generateNextCode(String code, boolean withDash) {
        String[] arr = code.split(SPLIT);
        int len = arr.length;
        String codePrefix = arr[FIRST];
        String codeSuffix = len > 1 ? arr[SECOND] : DEFAULT_SUFFIX;

        if (withDash) {
            codeSuffix = BaseUtils.computeCode(codeSuffix, true);
            return String.join(SPLIT, codePrefix, codeSuffix);
        } else {
            return BaseUtils.computeCode(codePrefix, true);
        }
    }
}
