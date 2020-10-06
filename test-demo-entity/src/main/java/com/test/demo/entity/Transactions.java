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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @Column(name = "trade_id")
    @ApiModelProperty(value = "贸易外键")
    private Integer tradeId;

    @ApiModelProperty(value = "版本")
    private Integer version;

    @Column(name = "security_code")
    @ApiModelProperty(value = "股票代码")
    private String securityCode;

    @ApiModelProperty(value = "股票数量")
    private Integer quantity;

    /**
     * insert/update/cancel
     */
    @ApiModelProperty(value = "insert/update/cancel")
    private String transactionType;

    /**
     * buy/sell
     */
    @ApiModelProperty(value = "buy/sell")
    private String type;

}