package com.ruoyi.city.service.impl;

import com.ruoyi.city.domain.LoginRequest;
import com.ruoyi.city.domain.Users;
import com.ruoyi.city.service.ILoginService;
import com.ruoyi.city.service.IUsersService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.*;
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IUsersService usersService;

    @Autowired
    private TokenService tokenService;

    @Value("${token.secret}")
    private String secret;

    @Override
    public AjaxResult login(LoginRequest request) {

        if (StringUtils.isEmpty(request.getUsername()) || StringUtils.isEmpty(request.getPassword())) {
            return AjaxResult.error("用户名或密码不能为空");
        }

        Users user = usersService.selectUserByUsername(request.getUsername());
        if (user == null) {
            return AjaxResult.error("用户不存在");
        }

        if (!Objects.equals(request.getPassword(), user.getPassword())) {
            return AjaxResult.error("密码错误");
        }

        String token = tokenService.createToken(getLoginUser(user));
        // 返回响应
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("userInfo", buildUserInfo(user));

        return AjaxResult.success(result);


    }

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Map<String, Object> buildUserInfo(Users user) {
        return new HashMap<String, Object>(){{
            put("userId", user.getId());
            put("username", user.getUsername());
            put("role", user.getRole());
            put("phone", user.getPhone());
        }};
    }

    public LoginUser getLoginUser(Users user) {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserType("field");
        loginUser.setUserId(user.getId());
        loginUser.setUsername(user.getUsername());
        return loginUser;
    }

}
