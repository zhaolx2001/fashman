package com.zhaolxun.freshman.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaolxun.freshman.entity.*;
import com.zhaolxun.freshman.entity.Class;
import com.zhaolxun.freshman.entity.vo.*;
import com.zhaolxun.freshman.mapper.UserInfoMapper;
import com.zhaolxun.freshman.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhaolxun
 * @since 2022-04-24
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private CampusService campusService;

    @Autowired
    private CollegesService collegesService;

    @Autowired
    private ClassService classService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private ProvincesService provincesService;

    @Override
    public UserInfo getPageStudent(UserInfoVo userInfoVo, Page<UserInfo> userInfoPage) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        // TODO: 2022/4/27 分页带条件查询
        return null;
    }

    @Override
    public Map<String, Object> getAllSta(Integer year) {
        HashMap<String, Object> map = new HashMap<>();


        //统计校区
        QueryWrapper<Campus> campusQueryWrapper = new QueryWrapper<>();
        campusQueryWrapper.eq("statistical_year", year);
        List<Campus> campusList = campusService.list(campusQueryWrapper);
        //统计学院
        //记录校区id
        ArrayList<String> campusIds = new ArrayList<>();
        for (Campus campus : campusList) {
            String id = campus.getId();
            campusIds.add(id);
        }

        QueryWrapper<Colleges> collegesQueryWrapper = new QueryWrapper<>();
        collegesQueryWrapper.eq("statistical_year", year);
        collegesQueryWrapper.in("campus_id", campusIds);
        List<Colleges> collegesList = collegesService.list(collegesQueryWrapper);

        //统计班级
        //记录学院id
        ArrayList<String> collegeIds = new ArrayList<>();
        for (Colleges colleges : collegesList) {
            String id = colleges.getId();
            collegeIds.add(id);
        }
        QueryWrapper<Class> classQueryWrapper = new QueryWrapper<>();
        classQueryWrapper.eq("statistical_year", year);
        classQueryWrapper.in("campus_id", campusIds);
        classQueryWrapper.in("colleges_id", collegeIds);
        List<Class> classList = classService.list(classQueryWrapper);

        //专业
        QueryWrapper<Major> majorQueryWrapper = new QueryWrapper<>();
        majorQueryWrapper.eq("statistical_year", year);
        List<Major> majorList = majorService.list(majorQueryWrapper);

        //省份
        QueryWrapper<Provinces> provincesQueryWrapper = new QueryWrapper<>();
        provincesQueryWrapper.eq("statistical_year", year);
        List<Provinces> provincesList = provincesService.list(provincesQueryWrapper);


        //整合
        //一级分类
        ArrayList<CampusVo> staList = new ArrayList<>();
//        HashMap<String,Object> sta = new HashMap<>();
//        //校区、学院、班级
//        ArrayList<Object> campuses = new ArrayList<>();
//        //major
//        ArrayList<Object> CollegesList = new ArrayList<>();
//        //province
//        ArrayList<Object> ClassList = new ArrayList<>();

////        sta.add(campuses);
//        sta.put("campuses",campuses);
//        campuses.add(CollegesList);
//        CollegesList.add(ClassList);

        //相同学院、班级信息相连
        for (Campus campus : campusList) {

            CampusVo campusVo = new CampusVo();
            BeanUtils.copyProperties(campus, campusVo);
            staList.add(campusVo);

            //二级分类
            ArrayList<CollegesVo> collegesVoArrayList = new ArrayList<>();
            for (Colleges colleges : collegesList) {
                CollegesVo collegesVo = new CollegesVo();
                if (colleges.getCampusId().equals(campus.getId())) {
                    //二级分类对象
                    BeanUtils.copyProperties(colleges, collegesVo);
                    collegesVoArrayList.add(collegesVo);

                }
                //三级分类
                ArrayList<ClassVo> classVoArrayList = new ArrayList<>();
                for (Class aClass : classList) {
                    if (aClass.getCollegesId().equals(colleges.getId())) {
                        ClassVo classVo = new ClassVo();
                        BeanUtils.copyProperties(aClass, classVo);
                        classVoArrayList.add(classVo);
                    }
                }
                collegesVo.setChildClass(classVoArrayList);
            }
            campusVo.setChildCollege(collegesVoArrayList);
        }
        //数据整合
        map.put("staList", staList);

        map.put("campusList", campusList);
        map.put("collegesList", collegesList);
        map.put("classList", classList);
        map.put("majorList", majorList);
        map.put("provincesList", provincesList);


        return map;
    }

    @Override
    public void addOneStudent(UserInfo userInfo) {
        //到校、入学
        Boolean isEnrolled = userInfo.getIsEnrolled();
        Boolean isEntrance = userInfo.getIsEntrance();
        if (isEnrolled && isEntrance) {
            //校区
            Campus campus = campusService.getById(userInfo.getCampusId());
            campus.setNumberReal(campus.getNumberReal() + 1);
            campusService.updateById(campus);
            //学院
            Colleges colleges = collegesService.getById(userInfo.getCollegesId());
            colleges.setNumberReal(colleges.getNumberReal() + 1);
            collegesService.updateById(colleges);
            //班级
            Class clazz = classService.getById(userInfo.getClassId());
            clazz.setNumberReal(clazz.getNumberReal() + 1);
            classService.updateById(clazz);
            //省份
            Provinces provinces = provincesService.getById(userInfo.getProvincesId());
            provinces.setNumberReal(provinces.getNumberReal() + 1);
            provincesService.updateById(provinces);
            //专业
            Major major = majorService.getById(userInfo.getMajorId());
            major.setNumberReal(major.getNumberReal() + 1);
            majorService.updateById(major);
        }
    }

    @Override
    public List<UserInfo> getStudentList(StudentListVo studentListVo) {

        return null;
    }
}
