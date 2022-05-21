package com.zhaolxun.freshman.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaolxun.freshman.entity.Campus;
import com.zhaolxun.freshman.entity.vo.CampusVo;
import com.zhaolxun.freshman.service.CampusService;
import com.zhaolxun.freshman.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.util.calendar.Gregorian;

import java.util.Date;
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
@Api(description = "校区管理")
@RestController
@RequestMapping("/freshman/campus")
@CrossOrigin
public class CampusController {

    @Autowired
    private CampusService campusService;

    /**
     * 添加校区
     * @param campus
     * @return
     */
    @ApiOperation(value = "添加校区")
    @PostMapping("add")
    public R addCampus(@RequestBody Campus campus){
//        System.out.println(campus);
//        Date date = campus.getStatisticalYear();
//        int year = date.getYear();
//        System.out.println(year);
        campusService.addCampus(campus);
        return R.ok();
    }

    /**
     * 修改校区
     * @param
     * @return
     */
    @ApiOperation(value = "修改校区")
    @PostMapping("update")
    public R updateCampus(@RequestBody Campus campus){
        campusService.updateById(campus);
        return R.ok();
    }

    /**
     * 删除校区
     * @param
     * @return
     */
    @ApiOperation(value = "删除校区")
    @DeleteMapping("delete/{id}")
    public R deleteCampus(@PathVariable String id){

        campusService.deleteCampus(id);
        return R.ok();
    }

    /**
     * 条件查询校区
     * @param campus 校区
     * @param page 当前页
     * @param limit 当前页显示记录数
     * @return
     */
    @ApiOperation(value = "条件查询校区")
    @GetMapping("list/{page}/{limit}")
    public R getPageCampus(@RequestBody Campus campus,@PathVariable long page, @PathVariable long limit){
        Page<Campus> campusPage = new Page<>(page, limit);
        Campus pageCampus = campusService.getPageCampus(campus,campusPage);
        return R.ok().data("campus",pageCampus);
    }


    /**
     * 得到全部校区
     * @param
     * @return
     */
    @ApiOperation(value = "得到全部校区")
    @GetMapping("all")
    public R getAllCampus(){

        List<Campus> list = campusService.list(null);
        return R.ok().data("list",list);
    }

    /**
     * 得到全部校区名字并去重
     * @param
     * @return
     */
    @ApiOperation(value = "得到全部校区并去重")
    @GetMapping("names")
    public R getAllCampusName(){

        List<Campus> list = campusService.getAllCampusName();
        return R.ok().data("list",list);
    }

    /**
     * 根据条件查询校区数据
     * @param
     * @return
     */
    @ApiOperation(value = "根据条件查询校区数据")
    @PostMapping("chart")
    public R showChart(@RequestBody CampusVo campusVo){
        Map<String,Object> map = campusService.showChart(campusVo);
        return R.ok().data("chart",map);
    }

    /**
     * 查询相同名字校区的统计年份，判断是否有相同年份
     * @param
     * @return
     */
    @ApiOperation(value = "判断是否有相同年份")
    @GetMapping("name/{campusName}/{year}")
    public R isSameName(@PathVariable String campusName,@PathVariable String year){
        int yearInt = Integer.parseInt(year);
        boolean isSameName = campusService.isSameName(campusName,yearInt);
        return R.ok().data("isSameName",isSameName);
    }


    /**
     * 根据年份查询校区
     * @param
     * @return
     */
    @ApiOperation(value = "根据年份查询各个校区")
    @PostMapping("/{year}")
    public R getCampusByYear(@PathVariable String year){
        int yearInt = Integer.parseInt(year);
        List<Campus> campusName = campusService.getCampusByYear(yearInt);
        return R.ok().data("campusName",campusName);
    }
}

