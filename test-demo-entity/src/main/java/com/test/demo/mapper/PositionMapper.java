package com.test.demo.mapper;


import com.test.demo.entity.Position;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PositionMapper extends Mapper<Position> {

    void updateQuantityByCode(@Param("quantity")Integer quantity,@Param("code")String code);
}