package com.ren.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ren.reggie.common.R;
import com.ren.reggie.entity.Employee;
import com.ren.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //页面提交密码md5加密处理
        String password = employee.getPassword();
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        //包装查询对象
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //查询数据库
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        //数据是否查到
        if(emp==null){
            return R.error("登录失败");
        }
        //密码比对
        if(!emp.getPassword().equals(password)){
            return R.error("登录失败");
        }
        //查看员工状态是否正常可用,如果禁用登录失败
        if(emp.getStatus()==0){
            return R.error("账号已禁用");
        }
        //登录成功  用户信息放到session 并返回登录结果
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    /**
     * 员工退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清理session中保存的员工id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
}
