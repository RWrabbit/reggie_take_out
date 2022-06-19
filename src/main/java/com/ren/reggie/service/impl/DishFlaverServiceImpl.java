package com.ren.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ren.reggie.entity.DishFlavor;
import com.ren.reggie.mapper.DishFlavorMapper;
import com.ren.reggie.service.DishFlaverService;
import org.springframework.stereotype.Service;

@Service
public class DishFlaverServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlaverService {
}
