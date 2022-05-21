package com.zhaolxun.freshman.controller;


import com.zhaolxun.freshman.entity.Campus;
import com.zhaolxun.freshman.entity.Colleges;
import com.zhaolxun.freshman.entity.vo.CampusVo;
import com.zhaolxun.freshman.entity.vo.CollegesVo;
import com.zhaolxun.freshman.service.CollegesService;
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
@Api(description = "学院管理")
@RestController
@RequestMapping("/freshman/colleges")
@CrossOrigin
public class CollegesController {

    @Autowired
    private CollegesService collegesService;


    /**
     * 查看各个学院的人数是否超过了校区
     * @param campus
     * @return
     */
    @ApiOperation(value = "统计相同校区各个学院的总人数包括真实人数和预期人数")
    @PostMapping("number/{campusId}")
    public R getNumber(@PathVariable String campusId){
        List<Integer> list  = collegesService.getNumber(campusId);
        return R.ok().data("list",list);
    }

    /**
     * 添加学院
     * @param
     * @return
     */
    @ApiOperation(value = "添加学院")
    @PostMapping("add")
    public R addCampus(@RequestBody Colleges colleges){
        collegesService.save(colleges);
        return R.ok();
    }

    /**
     * 修改学院
     * @param
     * @return
     */
    @ApiOperation(value = "修改学院")
    @PostMapping("update")
    public R updateCampus(@RequestBody Colleges colleges){
        collegesService.updateById(colleges);
        return R.ok();
    }

    /**
     * 删除学院
     * @param
     * @return
     */
    @ApiOperation(value = "删除学院")
    @DeleteMapping("delete/{id}")
    public R deleteCampus(@PathVariable String id){

        collegesService.removeById(id);
        return R.ok();
    }


    /**
     * 得到全部学院
     * @param
     * @return
     */
    @ApiOperation(value = "得到全部学院")
    @GetMapping("all")
    public R getAllCampus(){

        List<Colleges> list = collegesService.list(null);
        return R.ok().data("list",list);
    }

    /**
     * 得到全部学院名字并去重
     * @param
     * @return
     */
    @ApiOperation(value = "得到全部学院并去重")
    @GetMapping("name")
    public R getAllCampusName(){

        List<Colleges> list = collegesService.getAllName();
        return R.ok().data("list",list);
    }

    /**
     * 根据条件查询学院数据
     * @param
     * @return
     */
    @ApiOperation(value = "根据条件查询学院数据")
    @PostMapping("chart")
    public R showChart(@RequestBody CollegesVo collegesVo){
        Map<String,Object> map = collegesService.showChart(collegesVo);
        return R.ok().data("chart",map);
    }

    /**
     * 查询相同名字学院的统计年份，判断是否有相同年份
     * @param
     * @return
     */
    @ApiOperation(value = "判断是否有相同年份")
    @GetMapping("name/{collegeName}/{year}")
    public R isSameName(@PathVariable String collegeName,@PathVariable String year){
        int yearInt = Integer.parseInt(year);
        boolean isSameName = collegesService.isSameName(collegeName,yearInt);
        return R.ok().data("isSameName",isSameName);
    }


    /**
     * 根据校区id查询学院
     * @param
     * @return
     */
    @ApiOperation(value = "根据校区id查询学院")
    @PostMapping("/{campusId}")
    public R getCollegeByCampusId(@PathVariable String campusId){
        List<Colleges> colleges = collegesService.getCollegeByCampusId(campusId);
        return R.ok().data("colleges",colleges);
    }

    @ApiOperation(value = "带条件查询学院")
    @PostMapping("/getCollege")
    public R getCollege(@RequestBody CollegesVo collegesVo){

        List<Colleges> list  = collegesService.getCollege(collegesVo);

        return R.ok().data("list",list);
    }

}

