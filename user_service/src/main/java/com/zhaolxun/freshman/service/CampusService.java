package com.zhaolxun.freshman.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaolxun.freshman.entity.Campus;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhaolxun.freshman.entity.vo.CampusVo;

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
public interface CampusService extends IService<Campus> {

    void deleteCampus(String id);

    Campus getPageCampus(Campus campus, Page<Campus> campusPage);

    void addCampus(Campus campus);

    List<Campus> getAllCampusName();

    Map<String, Object> showChart(CampusVo campusVo);

    boolean isSameName(String campusName, int year);

    List<Campus> getCampusByYear(int yearInt);
}
