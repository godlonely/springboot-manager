package com.company.project.common.shiro;

import com.company.project.common.exception.BusinessException;
import com.company.project.common.exception.code.BaseResponseCode;
import com.company.project.service.HttpSessionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 认证
 */
public class CustomHashedCredentialsMatcher extends SimpleCredentialsMatcher {

    @Autowired
    private HttpSessionService httpSessionService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        if (httpSessionService.getCurrentSession() == null) {
            SecurityUtils.getSubject().logout();
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }
        return true;
    }
}