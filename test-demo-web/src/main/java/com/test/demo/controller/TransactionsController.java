package com.test.demo.controller;

import com.google.gson.JsonObject;
import com.test.demo.base.R;
import com.test.demo.dto.ResultDto;
import com.test.demo.dto.TransactionsDto;
import com.test.demo.entity.Position;
import com.test.demo.entity.Transactions;
import com.test.demo.mapper.PositionMapper;
import com.test.demo.mapper.TransactionsMapper;
import com.test.demo.service.PositionService;
import com.test.demo.service.TransactionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "交易", tags = "交易")
@RestController
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private PositionMapper positionMapper;

    @ApiOperation(value = "交易")
    @PostMapping("/transaction")
    public R<List<Position>> transaction(@RequestBody TransactionsDto dto){
         transactionsService.transaction(dto);
        List<Position> ls = positionMapper.selectAll();
       return R.success(ls);
    }

    @ApiOperation(value = "初始化数据")
    @GetMapping("/initData")
    public R<ResultDto> initData(){
       return positionService.initData();
    }
}
