package com.ren.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ren.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
