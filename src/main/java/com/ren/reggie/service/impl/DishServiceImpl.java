package com.ren.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ren.reggie.dto.DishDto;
import com.ren.reggie.entity.Dish;
import com.ren.reggie.entity.DishFlavor;
import com.ren.reggie.mapper.DishMapper;
import com.ren.reggie.service.DishFlaverService;
import com.ren.reggie.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlaverService dishFlaverService;
    /**
     * 新增菜品，同时保存对应的口味数据
     * @param dishDto
     */
    @Override
    @Transactional
    public void saveWithFlaver(DishDto dishDto) {
        //保存菜品的基本信息到菜品表
        this.save(dishDto);
        //菜品id
        Long dishId = dishDto.getId();
        //保存菜品口味数据到dish_flaver
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.stream().map((item)->{
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        dishFlaverService.saveBatch(flavors);

    }
}
