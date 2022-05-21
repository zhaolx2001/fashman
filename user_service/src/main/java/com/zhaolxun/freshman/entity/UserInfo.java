package com.zhaolxun.freshman.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@ApiModel(value="UserInfo对象", description="")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "新生id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "性别 0 女，1男")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "录取通知书编号")
    private String offerId;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "所属地表的id ")
    private String provincesId;

    @ApiModelProperty(value = "专业id")
    private String majorId;

    @ApiModelProperty(value = "校区id")
    private String campusId;

    @ApiModelProperty(value = "学院id")
    private String collegesId;

    @ApiModelProperty(value = "班级id")
    private String classId;

    @ApiModelProperty(value = "学制")
    private String education;

    @ApiModelProperty(value = "入学年份")
    private Integer enrollmentYear;

    @ApiModelProperty(value = "是否入学 1 表示是，0表示否")
    private Boolean isEntrance;

    @ApiModelProperty(value = "是否到校 1 表示是，0表示否")
    private Boolean isEnrolled;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
