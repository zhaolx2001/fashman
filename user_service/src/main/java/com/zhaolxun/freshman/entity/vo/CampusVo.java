package com.zhaolxun.freshman.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zhaolxun.freshman.entity.Colleges;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhao
 * @create 2022-04-25 21:21
 */
@Data
public class CampusVo {

    @ApiModelProperty(value = "新生入学校区的id ")
    private String id;

    @ApiModelProperty(value = "校区名称")
    private String name;

    @ApiModelProperty(value = "开始时间")
    private Integer begin;

    @ApiModelProperty(value = "结束时间")
    private Integer end;

    @ApiModelProperty(value = "对应学院")
    private List<CollegesVo> childCollege;
}
