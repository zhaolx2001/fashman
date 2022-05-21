package com.zhaolxun.freshman.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhao
 * @create 2022-04-26 22:28
 */
@Data
public class MajorVo {

    @ApiModelProperty(value = "专业名称")
    private String name;

    @ApiModelProperty(value = "开始时间")
    private Integer begin;

    @ApiModelProperty(value = "结束时间")
    private Integer end;
}
