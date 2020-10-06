package com.test.demo.dto;

import com.test.demo.entity.Position;
import com.test.demo.entity.Transactions;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {

    @ApiModelProperty(value = "股票数据")
    private List<Position> ls ;
    @ApiModelProperty(value = "交易数据")
    private List<Transactions> tls ;
}
