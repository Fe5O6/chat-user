package com.h.admin.intercepter;


import com.h.model.admin.pojos.ApUser;
import com.h.uils.thread.AdminThreadLocalUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器，拦截用户id
 */
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");
        //存入当前登陆用户的id
        if (userId != null) {
            ApUser apUser = new ApUser();
            apUser.setId(Integer.parseInt(userId));
            AdminThreadLocalUtil.setUser(apUser);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AdminThreadLocalUtil.clear();
    }
}
