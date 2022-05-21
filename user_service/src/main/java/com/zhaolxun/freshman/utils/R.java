package com.zhaolxun.freshman.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回信息  ,链式编程
 *
 * @author zhao
 * @create 2022-02-05 12:37
 */
@Data
public class R {
    @ApiModelProperty(value = "是否成功") //进行注释信息
    public boolean success;

    @ApiModelProperty(value = "状态码")
    public Integer code;

    @ApiModelProperty(value = "消息  success or error")
    public String message;

    @ApiModelProperty(value = "返回数据")
    public Map<String, Object> data = new HashMap<>();

    private R() {
    }

    ; // 进行私有化

    /**
     * 成功
     *
     * @return
     */
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    /**
     * 失败
     * @return
     */
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setMessage("失败");
        r.setCode(ResultCode.ERROR);
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }


}
