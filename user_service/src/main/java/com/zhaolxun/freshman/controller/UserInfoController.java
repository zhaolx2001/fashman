package com.zhaolxun.freshman.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaolxun.freshman.entity.UserInfo;
import com.zhaolxun.freshman.entity.vo.StudentListVo;
import com.zhaolxun.freshman.entity.vo.UserInfoVo;
import com.zhaolxun.freshman.service.UserInfoService;
import com.zhaolxun.freshman.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaolxun
 * @since 2022-04-24
 */
@Api(description = "新生管理")
@RestController
@RequestMapping("/freshman/user")
@CrossOrigin
public class UserInfoController {
    
    @Autowired
    private UserInfoService userInfoService;


    /**
     * 添加新生
     * @param
     * @return
     */
    @ApiOperation(value = "添加新生")
    @PostMapping("add")
    public R addStudent(@RequestBody UserInfo userInfo){
        userInfo.setOfferId("1234567");
        userInfoService.save(userInfo);
        return R.ok();
    }

    /**
     * 修改新生
     * @param
     * @return
     */
    @ApiOperation(value = "修改新生")
    @PostMapping("update")
    public R updateStudent(@RequestBody UserInfo userInfo){
        userInfoService.updateById(userInfo);
        return R.ok();
    }


    /**
     * 删除新生
     * @param
     * @return
     */
    @ApiOperation(value = "删除新生")
    @DeleteMapping("delete/{id}")
    public R deleteStudent(@PathVariable String id){

        userInfoService.removeById(id);
        return R.ok();
    }

    /**
     * 得到全部新生
     * @param
     * @return
     */
    @ApiOperation(value = "得到全部新生")
    @GetMapping("all")
    public R getAllStudent(){

        List<UserInfo> list = userInfoService.list(null);
        return R.ok().data("list",list);
    }

    /**
     * 条件查询新生
     * @param Student 新生
     * @param page 当前页
     * @param limit 当前页显示记录数
     * @return
     */
    @ApiOperation(value = "条件查询校区")
    @GetMapping("list/{page}/{limit}")
    public R getPageStudent(@RequestBody UserInfoVo userInfoVo,@PathVariable long page, @PathVariable long limit){
        Page<UserInfo> UserInfoPage = new Page<>(page, limit);
        UserInfo pageUserInfo = userInfoService.getPageStudent(userInfoVo,UserInfoPage);
        return R.ok().data("Student",pageUserInfo);
    }


    /**
     * 根据年份得到校区、学院、班级、专业等信息
     * @return
     */
    @GetMapping("getAllSta/{year}")
    @ApiOperation(value = "根据统计年份得到校区，学院，班级，专业的数据")
    public R getAllSta(@PathVariable Integer year){

        Map<String,Object> sta = userInfoService.getAllSta(year);
        return R.ok().data("sta",sta);
    }

    /**
     * 将学生所在的班级、学院等等入学人数增加一个人
     * @return
     */
    @ApiOperation(value = "将学生所在的班级、学院等等入学人数增加一个人")
    @PostMapping("addOneStudent")
    public R addOneStudent(@RequestBody UserInfo userInfo){
        userInfoService.addOneStudent(userInfo);
        return R.ok();
    }

    /**
     * 学生列表条件查询
     * @return
     */
    @ApiOperation(value = "学生列表条件查询")
    @PostMapping("select")
    public R getStudentList(@RequestBody StudentListVo studentListVo){
        List<UserInfo> list = userInfoService.getStudentList(studentListVo);
        return R.ok().data("list",list);
    }


}

