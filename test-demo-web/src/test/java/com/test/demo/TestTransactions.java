package com.test.demo;

import com.test.demo.dto.TransactionsDto;
import com.test.demo.mapper.PositionMapper;
import com.test.demo.service.PositionService;
import com.test.demo.service.TransactionsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTransactions {

    @Autowired
    private TransactionsService transactionsService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private PositionMapper positionMapper;

    @Test
    public void testTransaction(){

        positionService.initData();

        transactionsService.transaction(TransactionsDto.builder()
                .tradeId(1)
                .quantity(50)
                .securityCode("REL")
                .type("buy").build());

        transactionsService.transaction(TransactionsDto.builder()
                .tradeId(2)
                .quantity(40)
                .securityCode("ITC")
                .type("Sell").build());

        transactionsService.transaction(TransactionsDto.builder()
                .tradeId(3)
                .quantity(70)
                .securityCode("INF")
                .type("buy").build());

        transactionsService.transaction(TransactionsDto.builder()
                .tradeId(1)
                .quantity(60)
                .securityCode("REL")
                .type("buy").build());

        transactionsService.transaction(TransactionsDto.builder()
                .tradeId(2)
                .quantity(30)
                .securityCode("INF")
                .type("cancel").build());

        positionMapper.selectAll().forEach(item->{
            System.out.println(item.getName()+"==>"+item.getQuantity());
        });

    }
}
