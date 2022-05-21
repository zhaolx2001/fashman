package com.zhaolxun.freshman.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaolxun
 * @since 2022-04-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Colleges对象", description="")
public class Colleges implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "新生入学学院的id ")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "校区id")
    private String campusId;

    @ApiModelProperty(value = "学院名称")
    private String name;

    @ApiModelProperty(value = "实招人数")
    private Integer numberReal;

    @ApiModelProperty(value = "预计人数")
    private Integer numberExpect;

    @ApiModelProperty(value = "统计年份")
    private Integer statisticalYear;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;



}
