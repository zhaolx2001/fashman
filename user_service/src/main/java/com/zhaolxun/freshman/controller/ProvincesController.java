package com.zhaolxun.freshman.controller;


import com.zhaolxun.freshman.entity.Major;
import com.zhaolxun.freshman.entity.Provinces;
import com.zhaolxun.freshman.entity.vo.MajorVo;
import com.zhaolxun.freshman.entity.vo.ProvincesVo;
import com.zhaolxun.freshman.service.ProvincesService;
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
@Api(description = "所属地管理")
@RestController
@RequestMapping("/freshman/provinces")
@CrossOrigin
public class ProvincesController {
    
    @Autowired
    private ProvincesService provincesService;


    /**
     * 查看该年份各个省份的人数
     * @param
     * @return
     */
    @ApiOperation(value = "查看该年份各个省份的人数")
    @GetMapping("number/{year}")
    public R getNumber(@PathVariable Integer year){
        List<ProvincesVo> list  = provincesService.getNumber(year);
        return R.ok().data("list",list);
    }

    /**
     * 添加学院
     * @param
     * @return
     */
    @ApiOperation(value = "添加学院")
    @PostMapping("add")
    public R addCampus(@RequestBody Provinces provinces){
        provincesService.save(provinces);
        return R.ok();
    }

    /**
     * 修改学院
     * @param
     * @return
     */
    @ApiOperation(value = "修改学院")
    @PostMapping("update")
    public R updateCampus(@RequestBody Provinces provinces){
        provincesService.updateById(provinces);
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

        provincesService.removeById(id);
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

        List<Provinces> list = provincesService.list(null);
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

        List<Provinces> list = provincesService.getAllName();
        return R.ok().data("list",list);
    }


    /**
     * 根据条件查询学院数据
     * @param
     * @return
     */
    @ApiOperation(value = "根据条件查询学院数据")
    @PostMapping("chart")
    public R showChart(@RequestBody ProvincesVo provincesVo){
        Map<String,Object> map = provincesService.showChart(provincesVo);
        return R.ok().data("chart",map);
    }

    /**
     * 查询相同名字学院的统计年份，判断是否有相同年份
     * @param
     * @return
     */
    @ApiOperation(value = "判断是否有相同年份")
    @GetMapping("name/{provincesName}/{year}")
    public R isSameName(@PathVariable String provincesName,@PathVariable String year){
        int yearInt = Integer.parseInt(year);
        boolean isSameName = provincesService.isSameName(provincesName,yearInt);
        return R.ok().data("isSameName",isSameName);
    }

    /**
     * 根据年份和省份名字添加一条记录，并且判断是否有相同，相同就不用增加
     * @param
     * @return
     */
    @ApiOperation(value = "根据年份和省份名字添加一条记录，并且判断是否有相同，相同就不用增加")
    @PostMapping("add/{provincesName}/{year}")
    public R addProvincesByInsStudent(@PathVariable String provincesName,@PathVariable Integer year){
       provincesService.addProvincesByInsStudent(provincesName,year);
        //没有数据
        return R.ok();
    }

    /**
     * 根据名称和年份得到数据
     * @param
     * @return
     */
    @ApiOperation(value = "根据名称和年份得到数据")
    @GetMapping("get/{provincesName}/{year}")
    public R getProvince(@PathVariable String provincesName,@PathVariable Integer year){
        List<Provinces> provincesList = provincesService.getProvince(provincesName,year);
        Provinces provinces = provincesList.get(0);
        return R.ok().data("province",provinces);
    }

}

