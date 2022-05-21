package com.zhaolxun.freshman.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zhaolxun.freshman.entity.Class;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhao
 * @create 2022-04-26 21:09
 */
@Data
public class CollegesVo {

    @ApiModelProperty(value = "新生入学学院的id ")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "校区id")
    private String campusId;

    @ApiModelProperty(value = "学院名称")
    private String name;

    @ApiModelProperty(value = "校区名称")
    private String campusName;


    @ApiModelProperty(value = "开始时间")
    private Integer begin;

    @ApiModelProperty(value = "结束时间")
    private Integer end;

    @ApiModelProperty(value = "对应班级")
    private List<ClassVo> childClass;
}
