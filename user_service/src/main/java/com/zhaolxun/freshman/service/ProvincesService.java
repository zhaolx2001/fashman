package com.zhaolxun.freshman.service;

import com.zhaolxun.freshman.entity.Major;
import com.zhaolxun.freshman.entity.Provinces;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhaolxun.freshman.entity.vo.ProvincesVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaolxun
 * @since 2022-04-24
 */
public interface ProvincesService extends IService<Provinces> {

    List<ProvincesVo> getNumber(Integer year);

    List<Provinces> getAllName();

    Map<String, Object> showChart(ProvincesVo provincesVo);

    boolean isSameName(String provincesName, int yearInt);

    void addProvincesByInsStudent(String provincesName, Integer year);

    List<Provinces> getProvince(String provincesName, Integer year);
}
