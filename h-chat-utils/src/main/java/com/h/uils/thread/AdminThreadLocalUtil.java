package com.h.uils.thread;


import com.h.model.admin.pojos.ApUser;

public class AdminThreadLocalUtil {


    private static final ThreadLocal<ApUser> WM_USER_THREAD_LOCAL = new ThreadLocal<>();

    //存数据
    public static void setUser(ApUser adUser){
        WM_USER_THREAD_LOCAL.set(adUser);
    }

    //获取数据
    public static ApUser getUser(){
        return WM_USER_THREAD_LOCAL.get();
    }

    //清理
    public static void clear(){
        WM_USER_THREAD_LOCAL.remove();
    }
}
