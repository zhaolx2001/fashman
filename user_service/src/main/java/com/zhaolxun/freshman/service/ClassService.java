package com.zhaolxun.freshman.service;

import com.zhaolxun.freshman.entity.Class;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhaolxun.freshman.entity.Colleges;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaolxun
 * @since 2022-04-24
 */
public interface ClassService extends IService<Class> {

    List<Integer> getNumber(String campusId, String collegeId);

    List<Class> getAllName();

    List<Class> getClassByCampusId(String collegeId);

    boolean isSameName(String campusName, Integer year);
}
