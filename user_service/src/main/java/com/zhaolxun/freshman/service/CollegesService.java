package com.zhaolxun.freshman.service;

import com.zhaolxun.freshman.entity.Colleges;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhaolxun.freshman.entity.vo.CollegesVo;

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
public interface CollegesService extends IService<Colleges> {

    List<Integer> getNumber(String campusId);

    List<Colleges> getAllName();

    Map<String, Object> showChart(CollegesVo collegesVo);

    boolean isSameName(String collegeName, int yearInt);

    List<Colleges> getCollegeByCampusId(String campusId);

    List<Colleges> getCollege(CollegesVo collegesVo);
}
