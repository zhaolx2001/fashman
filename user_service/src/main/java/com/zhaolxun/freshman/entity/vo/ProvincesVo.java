package com.zhaolxun.freshman.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhao
 * @create 2022-04-26 22:37
 */
@Data
public class ProvincesVo {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "人数")
    private Integer value;
}
