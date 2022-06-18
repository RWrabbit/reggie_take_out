package com.ren.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ren.reggie.common.CustomException;
import com.ren.reggie.entity.Category;
import com.ren.reggie.entity.Dish;
import com.ren.reggie.entity.Setmeal;
import com.ren.reggie.mapper.CategoryMapper;
import com.ren.reggie.service.CategoryService;
import com.ren.reggie.service.DishService;
import com.ren.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;
    /**
     * 根据id删除分类，删除之前需要进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        //查询当前分类是否关联了菜品,如果已经关联，则排除业务异常
        LambdaQueryWrapper<Dish> queryDishWrapper=new LambdaQueryWrapper<>();
        //添加查询条件
        queryDishWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(queryDishWrapper);
        if(count>0){
            //关联了菜品抛出一个业务异常
            throw new CustomException("当前分类已经有菜品不能删除!");
        }
        //查询当前分类是否关联了套餐,如果已经关联，则排除业务异常
        LambdaQueryWrapper<Setmeal> querySetmealWrapper=new LambdaQueryWrapper<>();
        querySetmealWrapper.eq(Setmeal::getCategoryId,id);
        count = setmealService.count(querySetmealWrapper);
        if(count>0){
            //关联了套餐抛出一个业务异常
            throw new CustomException("当前分类已经有套餐不能删除!");
        }
        //正常删除分类
        super.removeById(id);
    }
}
