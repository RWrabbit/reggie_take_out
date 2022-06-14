package com.ren.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ren.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
