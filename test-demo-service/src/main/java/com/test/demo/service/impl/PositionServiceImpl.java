package com.test.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.test.demo.base.R;
import com.test.demo.dto.ResultDto;
import com.test.demo.dto.TransactionsDto;
import com.test.demo.entity.Position;
import com.test.demo.entity.Transactions;
import com.test.demo.exception.BizException;
import com.test.demo.mapper.PositionMapper;
import com.test.demo.mapper.TransactionsMapper;
import com.test.demo.service.PositionService;
import com.test.demo.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Transactional(rollbackFor = Exception.class)
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private PositionService positionService;
    @Autowired
    private TransactionsService transactionsService;
    @Autowired
    private TransactionsMapper transactionsMapper;

    private static final Lock lock = new ReentrantLock(false);

    /**
     * 数据初始化
     */
    @Override
    public void initPositionData(){
        positionMapper.insert(Position.builder().name("REL").quantity(0).build());
        positionMapper.insert(Position.builder().name("ITC").quantity(0).build());
        positionMapper.insert(Position.builder().name("INF").quantity(0).build());
    }

   /***
   * @Description: buy 买入，并发加锁
   * @Param: []
   * @return: void
   * @Author: John
   * @Date: 2020/10/4 12:00
   **/
   @Override
    public void buy(String position,Integer quantity){
        Position model =positionMapper.selectOne(Position.builder().name(position).build());
        if(ObjectUtil.isEmpty(model)){
            throw new BizException("position is empty");
        }
        lock.lock();
        try{
            model.setQuantity(quantity + model.getQuantity());
            positionMapper.updateQuantityByCode(model.getQuantity(),model.getName());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

   /***
   * @Description:  卖出 ，并发加锁
   * @Param: []
   * @return: void
   * @Author: John
   * @Date: 2020/10/4 12:00
   **/
   @Override
    public void sell(String position,Integer quantity){
        Position model =positionMapper.selectOne(Position.builder().name(position).build());
        if(ObjectUtil.isEmpty(model)){
            throw new BizException("position is empty");
        }
        lock.lock();
        try{
            if(model.getQuantity() - quantity>0){
                model.setQuantity(model.getQuantity() - quantity);
            }else{
                model.setQuantity(0);
            }
            positionMapper.updateQuantityByCode(model.getQuantity(),model.getName());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public R<ResultDto> initData(){
        positionService.initPositionData();

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

        List<Position> ls = positionMapper.selectAll();
        List<Transactions> tls = transactionsMapper.selectAll();
        return R.success(ResultDto.builder().ls(ls).tls(tls).build());
    }
}
