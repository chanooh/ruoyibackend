package com.ruoyi.city.controller;

// 小程序 登录
import com.ruoyi.city.domain.LoginRequest;
import com.ruoyi.city.service.IReportsService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.ruoyi.city.service.ILoginService;

import static com.ruoyi.common.core.domain.AjaxResult.success;

@RestController
@RequestMapping("/city/api")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginRequest request) {
        return success(loginService.login(request));

    }


}

