package com.zhaolxun.freshman.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaolxun.freshman.entity.Campus;
import com.zhaolxun.freshman.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhaolxun.freshman.entity.vo.StudentListVo;
import com.zhaolxun.freshman.entity.vo.UserInfoVo;

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
public interface UserInfoService extends IService<UserInfo> {

    UserInfo getPageStudent(UserInfoVo userInfoVo, Page<UserInfo> userInfoPage);

    Map<String, Object> getAllSta(Integer year);

    void addOneStudent(UserInfo userInfo);

    List<UserInfo> getStudentList(StudentListVo studentListVo);
}
