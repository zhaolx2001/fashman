package com.zhaolxun.freshman.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhaolxun.freshman.entity.Campus;
import com.zhaolxun.freshman.entity.Class;
import com.zhaolxun.freshman.entity.Colleges;
import com.zhaolxun.freshman.entity.Major;
import com.zhaolxun.freshman.entity.vo.CampusVo;
import com.zhaolxun.freshman.entity.vo.ClassVo;
import com.zhaolxun.freshman.entity.vo.CollegesVo;
import com.zhaolxun.freshman.service.CampusService;
import com.zhaolxun.freshman.service.ClassService;
import com.zhaolxun.freshman.service.CollegesService;
import com.zhaolxun.freshman.service.MajorService;
import com.zhaolxun.freshman.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaolxun
 * @since 2022-04-24
 */
@Api(description = "班级管理")
@RestController
@RequestMapping("/freshman/class")
@CrossOrigin
public class ClassController {
    @Autowired
    private ClassService classService;

    @Autowired
    private CampusService campusService;

    @Autowired
    private CollegesService collegesService;

    @Autowired
    private MajorService majorService;


    /**
     * 查看各个班级的预测人数是否超过了校区
     * @param
     * @return
     */
    @ApiOperation(value = "查看班级的人数")
    @PostMapping("number/{campusId}/{collegeId}")
    public R getNumber(@PathVariable String campusId,@PathVariable String collegeId){
        List<Integer> list  = classService.getNumber(campusId,collegeId);
        return R.ok().data("list",list);
    }


    /**
     * 添加班级
     * @param
     * @return
     */
    @ApiOperation(value = "添加班级")
    @PostMapping("add")
    public R addCampus(@RequestBody Class classs){

        //添加班级时更新学院、校区、专业更新人数数据
        //班级
        QueryWrapper<Campus> campusQueryWrapper = new QueryWrapper<>();
        campusQueryWrapper.eq("id", classs.getCampusId());
        Campus campus = campusService.getOne(campusQueryWrapper);
        campus.setNumberExpect(classs.getNumberExpect() + campus.getNumberExpect());
        campusService.updateById(campus);
        //学院
        QueryWrapper<Colleges> collegesQueryWrapper = new QueryWrapper<>();
        campusQueryWrapper.eq("id", classs.getCollegesId());
        Colleges colleges = collegesService.getOne(collegesQueryWrapper);
        colleges.setNumberExpect(classs.getNumberExpect() + colleges.getNumberExpect());
        collegesService.updateById(colleges);
        //专业
        QueryWrapper<Major> majorQueryWrapper = new QueryWrapper<>();
        campusQueryWrapper.eq("id", classs.getMajorId());
        Major major = majorService.getOne(majorQueryWrapper);
        major.setNumberExpect(classs.getNumberExpect() + major.getNumberExpect());
        majorService.updateById(major);

        classService.save(classs);
        return R.ok();
    }

    /**
     * 修改班级
     * @param
     * @return
     */
    @ApiOperation(value = "修改班级")
    @PostMapping("update")
    public R updateCampus(@RequestBody Class classs){
        classService.updateById(classs);
        return R.ok();
    }



    /**
     * 删除班级
     * @param
     * @return
     */
    @ApiOperation(value = "删除班级")
    @DeleteMapping("delete/{id}")
    public R deleteCampus(@PathVariable String id){

        classService.removeById(id);
        return R.ok();
    }

    /**
     * 得到全部班级
     * @param
     * @return
     */
    @ApiOperation(value = "得到全部班级")
    @GetMapping("all")
    public R getAllCampus(){

        List<Class> list = classService.list(null);
        return R.ok().data("list",list);
    }


    /**
     * 得到全部班级名字并去重
     * @param
     * @return
     */
    @ApiOperation(value = "得到全部班级并去重")
    @GetMapping("name")
    public R getAllCampusName(){

        List<Class> list = classService.getAllName();
        return R.ok().data("list",list);
    }


    /**
     * 根据学院id查询班级
     * @param
     * @return
     */
    @ApiOperation(value = "根据学院id查询班级")
    @PostMapping("/{collegeId}")
    public R getClassByCampusId(@PathVariable String collegeId){
        List<Class> classes = classService.getClassByCampusId(collegeId);
        return R.ok().data("classes",classes);
    }


    /**
     * 判断是否有相同年份
     * @param
     * @return
     */
    @ApiOperation(value = "判断是否有相同年份")
    @PostMapping("name/{className}/{year}")
    public R isSameName(@PathVariable String className,@PathVariable Integer year){
        boolean isSameName = classService.isSameName(className,year);
        return R.ok().data("isSameName",isSameName);
    }


    /**
     * 根据条件查询班级数据
     * @param
     * @return
     */
    @ApiOperation(value = "根据条件查询班级数据")
    @GetMapping("sta/{begin}/{end}")
    public R getSta(@PathVariable String begin,@PathVariable String end){
        QueryWrapper<Class> classQueryWrapper = new QueryWrapper<>();
        classQueryWrapper.between("statistical_year", begin, end);
        List<Class> list = classService.list(classQueryWrapper);
        return R.ok().data("list",list);
    }


}

