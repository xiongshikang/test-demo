package com.test.demo.service;

import com.test.demo.base.R;
import com.test.demo.dto.TransactionsDto;

/***
* @Description: 交易服务类
* @Param:
* @return:
* @Author: John
* @Date: 2020/10/4 11:57
**/
public interface TransactionsService {


    /***
     * @Description: 交易
     *   交易分两类， 交易过程中控制事物，保证数据事物安全、数据并发安全
     * @Param: [] transaction/cancel
     * @return: com.test.demo.base.R<java.lang.String>
     * @Author: John
     * @Date: 2020/10/4 17:21
     **/
     R<String> transaction(TransactionsDto item);


}
