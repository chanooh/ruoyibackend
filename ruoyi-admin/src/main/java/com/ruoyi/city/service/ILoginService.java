package com.ruoyi.city.service;

import com.ruoyi.city.domain.LoginRequest;
import com.ruoyi.common.core.domain.AjaxResult;

public interface ILoginService {

    AjaxResult login(LoginRequest request);
}
