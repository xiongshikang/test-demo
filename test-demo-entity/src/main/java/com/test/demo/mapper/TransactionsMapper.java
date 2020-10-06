package com.test.demo.mapper;



import com.test.demo.entity.Transactions;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface TransactionsMapper extends Mapper<Transactions> {

    /***
    * @Description: 通过股票代码与交易外键查询出最后一个版本的数据
    * @Param: [securityCode, tradeId] [股票代码，交易外键]
    * @return: com.test.demo.entity.Transactions
    * @Author: John
    * @Date: 2020/10/5 11:41
    **/
    Transactions selectForVersionNext(@Param("securityCode")String securityCode,
                                             @Param("tradeId")Integer tradeId);
}