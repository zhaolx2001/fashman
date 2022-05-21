package com.zhaolxun.freshman.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhao
 * @create 2022-05-01 10:16
 */
@Data
public class StudentListVo {

    @ApiModelProperty(value = "名字")
    private String studentName;

    @ApiModelProperty(value = "校区名字")
    private String campusName;

    @ApiModelProperty(value = "学院名字")
    private String collegeName;

    @ApiModelProperty(value = "班级名")
    private String className;

    @ApiModelProperty(value = "专业名")
    private String majorName;

    @ApiModelProperty(value = "名字")
    private Integer startYear;


}
