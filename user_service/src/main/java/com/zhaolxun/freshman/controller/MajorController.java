package com.zhaolxun.freshman.controller;


import com.zhaolxun.freshman.entity.Class;
import com.zhaolxun.freshman.entity.Colleges;
import com.zhaolxun.freshman.entity.Major;
import com.zhaolxun.freshman.entity.vo.CollegesVo;
import com.zhaolxun.freshman.entity.vo.MajorVo;
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
 * 前端控制器
 * </p>
 *
 * @author zhaolxun
 * @since 2022-04-24
 */
@Api(description = "专业管理")
@RestController
@RequestMapping("/freshman/major")
@CrossOrigin
public class MajorController {

    @Autowired
    private MajorService majorService;

    /**
     * 跟据名称、开始时间、结束时间查询该专业在该时间段的数据
     *
     * @param
     * @return
     */
    @ApiOperation(value = "跟据名称、开始时间、结束时间查询该专业在该时间段的数据")
    @GetMapping("get/{name}/{begin}/{end}")
    public R getMajor(@PathVariable String name, @PathVariable Integer begin, @PathVariable Integer end) {
        List<Major> list = majorService.getMajor(name,begin,end);
        return R.ok().data("list",list);
    }


    /**
     * 查看各个专业的人数是否超过了校区
     *
     * @param
     * @return
     */
    @ApiOperation(value = "统计相同校区各个专业的总人数包括真实人数和预期人数")
    @PostMapping("number")
    public R getNumber() {
        List<Integer> list = majorService.getNumber();
        return R.ok().data("list", list);
    }

    /**
     * 添加专业
     *
     * @param
     * @return
     */
    @ApiOperation(value = "添加专业")
    @PostMapping("add")
    public R addCampus(@RequestBody Major major) {
        majorService.save(major);
        return R.ok();
    }

    /**
     * 修改专业
     *
     * @param
     * @return
     */
    @ApiOperation(value = "修改专业")
    @PostMapping("update")
    public R updateCampus(@RequestBody Major major) {
        majorService.updateById(major);
        return R.ok();
    }


    /**
     * 删除专业
     *
     * @param
     * @return
     */
    @ApiOperation(value = "删除专业")
    @DeleteMapping("delete/{id}")
    public R deleteCampus(@PathVariable String id) {

        majorService.removeById(id);
        return R.ok();
    }

    /**
     * 得到全部专业
     *
     * @param
     * @return
     */
    @ApiOperation(value = "得到全部专业")
    @GetMapping("all")
    public R getAllCampus() {

        List<Major> list = majorService.list(null);
        return R.ok().data("list", list);
    }


    /**
     * 得到全部专业名字并去重
     *
     * @param
     * @return
     */
    @ApiOperation(value = "得到全部专业并去重")
    @GetMapping("name")
    public R getAllCampusName() {

        List<Major> list = majorService.getAllName();
        return R.ok().data("list", list);
    }


    /**
     * 根据条件查询专业数据
     *
     * @param
     * @return
     */
    @ApiOperation(value = "根据条件查询专业数据")
    @PostMapping("chart")
    public R showChart(@RequestBody MajorVo majorVo) {
        Map<String, Object> map = majorService.showChart(majorVo);
        return R.ok().data("chart", map);
    }

    /**
     * 查询相同名字专业的统计年份，判断是否有相同年份
     *
     * @param
     * @return
     */
    @ApiOperation(value = "判断是否有相同年份")
    @GetMapping("name/{majorName}/{year}")
    public R isSameName(@PathVariable String majorName, @PathVariable String year) {
        int yearInt = Integer.parseInt(year);
        boolean isSameName = majorService.isSameName(majorName, yearInt);
        return R.ok().data("isSameName", isSameName);
    }

    /**
     * 根据班级id查询专业
     *
     * @param
     * @return
     */
    @ApiOperation(value = "根据学院id查询班级")
    @PostMapping("/{classId}")
    public R getMajorByClassId(@PathVariable String classId) {
        List<Major> majorList = majorService.getMajorByClassId(classId);
        return R.ok().data("majorList", majorList);
    }
}

