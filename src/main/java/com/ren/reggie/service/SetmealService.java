package com.ren.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ren.reggie.dto.SetmealDto;
import com.ren.reggie.entity.Setmeal;

public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);
}
