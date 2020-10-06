package com.test.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/***
* @Description:
* @Param:
* @return:
* @Author: John
* @Date: 2020/10/4 11:54
**/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsDto {

    @Column(name = "trade_id")
    @ApiModelProperty(value = "贸易外键")
    private Integer tradeId;

    @Column(name = "股票代码")
    @ApiModelProperty(value = "股票代码")
    private String securityCode;

    @ApiModelProperty(value = "股票数量")
    private Integer quantity;

    /**
     * buy/sell
     */
    @ApiModelProperty(value = "buy/sell/cancel")
    private String type;

}
