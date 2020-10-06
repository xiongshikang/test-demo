package com.test.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.test.demo.base.R;
import com.test.demo.dto.TransactionsDto;
import com.test.demo.entity.Transactions;
import com.test.demo.mapper.TransactionsMapper;
import com.test.demo.service.PositionService;
import com.test.demo.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private TransactionsMapper transactionsMapper;

    @Autowired
    private PositionService positionService;

    /***
     * @Description: 交易
     *   交易分两类， 交易过程中控制事物，保证数据事物安全、数据并发安全
     * @Param: [] transaction/cancel
     * @return: com.test.demo.base.R<java.lang.String>
     * @Author: John
     * @Date: 2020/10/4 17:21
     **/
    @Override
    public R<String> transaction(TransactionsDto item){
        Transactions transactions = transactionsMapper.selectForVersionNext(item.getSecurityCode(),item.getTradeId());
        /**
         * 正常交易,1.判断insert/update  2.更新版本
         */
        if(ObjectUtil.isEmpty(transactions)){
            /**
             * 第一次交易，为insert
             */
            Transactions entity = BeanUtil.copyProperties(item,Transactions.class);
            entity.setTransactionType("insert");
            entity.setVersion(1);
            transactionsMapper.insert(entity);
            if(item.getType().equals("buy")){
                positionService.buy(item.getSecurityCode(),item.getQuantity());
            }else{
                positionService.sell(item.getSecurityCode(),item.getQuantity());
            }
        }else{
            if(transactions.getType().equals("buy")){
                /**
                 * 买入
                 */
                Transactions entity = BeanUtil.copyProperties(item,Transactions.class);
                entity.setVersion(transactions.getVersion()+1);
                entity.setTransactionType("update");
                entity.setType("buy");
                transactionsMapper.insert(entity);
                positionService.buy(entity.getSecurityCode(),entity.getQuantity());
            }else if(transactions.getType().equals("sell")){
                /**
                 * 卖出
                 */
                Transactions entity = BeanUtil.copyProperties(item,Transactions.class);
                entity.setVersion(transactions.getVersion()+1);
                entity.setTransactionType("update");
                entity.setType("sell");
                transactionsMapper.insert(entity);
                positionService.sell(entity.getSecurityCode(),entity.getQuantity());
            }else{
                /**
                 * 取消交易
                 */
                Transactions entity = BeanUtil.copyProperties(item,Transactions.class);
                entity.setVersion(transactions.getVersion()+1);
                entity.setTransactionType("cancel");
                entity.setType(item.getType());
                transactionsMapper.insert(entity);
            }
        }
       return R.success("交易成功");
    }
}
