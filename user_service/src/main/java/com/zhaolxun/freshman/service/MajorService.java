package com.zhaolxun.freshman.service;

import com.zhaolxun.freshman.entity.Colleges;
import com.zhaolxun.freshman.entity.Major;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhaolxun.freshman.entity.vo.MajorVo;

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
public interface MajorService extends IService<Major> {

    List<Integer> getNumber();

    List<Major> getAllName();

    Map<String, Object> showChart(MajorVo majorVo);

    boolean isSameName(String majorName, int yearInt);

    List<Major> getMajorByClassId(String classId);

    List<Major> getMajor(String name, Integer begin, Integer end);
}
