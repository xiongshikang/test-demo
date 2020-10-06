package com.test.demo.service;

import com.test.demo.base.R;
import com.test.demo.dto.ResultDto;

/***
* @Description:
* @Param:
* @return:
* @Author: John
* @Date: 2020/10/4 11:57
**/
public interface PositionService {

    /**
     * 数据初始化
     */
    void initPositionData();

    /**
     * 数据初始化
     */
    R<ResultDto> initData();

    /***
     * @Description: buy 买入，并发加锁
     * @Param: []
     * @return: void
     * @Author: John
     * @Date: 2020/10/4 12:00
     **/
     void buy(String position,Integer quantity);


    /***
     * @Description:  卖出 ，并发加锁
     * @Param: []
     * @return: void
     * @Author: John
     * @Date: 2020/10/4 12:00
     **/
     void sell(String position,Integer quantity);

}
