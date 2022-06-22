package com.ren.reggie.dto;

import com.ren.reggie.entity.Setmeal;
import com.ren.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
