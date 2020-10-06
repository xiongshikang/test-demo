package com.test.demo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
/***
* @Description:
* @Param:
* @return:
* @Author: John
* @Date: 2020/9/30 14:54
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Position {

    /**
     * 品种
     */
    @ApiModelProperty(value = "品种")
    private String name;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer quantity;


}