package com.ren.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ren.reggie.dto.SetmealDto;
import com.ren.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);

    /**
     *删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
