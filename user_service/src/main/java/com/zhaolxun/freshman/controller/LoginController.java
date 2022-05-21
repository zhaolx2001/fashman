package com.zhaolxun.freshman.controller;

import com.zhaolxun.freshman.utils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * 登录接口
 * @author zhao
 * @create 2022-02-14 20:49
 */
@Api(description = "登录")
@RestController
@CrossOrigin   //解决跨域问题
@RequestMapping("freshman/user")
public class LoginController {

    //登录
    @PostMapping("login")
    public R login(){
        return R.ok().data("taken","admin");
    }
    //个人信息
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
