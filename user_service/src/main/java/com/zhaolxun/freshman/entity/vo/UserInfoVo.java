package com.zhaolxun.freshman.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhao
 * @create 2022-04-26 22:49
 */
@Data
public class UserInfoVo {

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "性别 0 女，1男")
    private Integer sex;

    @ApiModelProperty(value = "专业名称")
    private String majorName;

    @ApiModelProperty(value = "校区名称")
    private String campusName;

    @ApiModelProperty(value = "学院名称")
    private String collegesName;

    @ApiModelProperty(value = "班级名称")
    private String className;

}
