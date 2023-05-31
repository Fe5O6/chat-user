//package com.h.admin.aspectj;
//
//import com.alibaba.fastjson.JSON;
//
//import com.h.admin.annotation.UserLog;
//import com.h.admin.pojos.AdUser;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.Date;
//
///**
// *  自定义平台管理端操作日志记录
// */
//@Aspect
//@Component
//@Slf4j
//public class UserLogAspect {
//
//    @Autowired
//    private AdUserMapper adUserMapper;
//
//    @Autowired
//    private AdUserLogMapper adUserLogMapper;
//
//    /**
//     * 切入点
//     */
//    @Pointcut("@annotation(com.heima.admin.annotation.UserLog)")
//    public void userLogPointCut(){
//    }
//
//    /**
//     *  后置增强
//     * @param joinPoint 连接点
//     * @param jsonResult
//     */
//    @AfterReturning(pointcut = "userLogPointCut()", returning = "jsonResult")
//    public void handleAfterReturning(JoinPoint joinPoint, Object jsonResult){
//        handleUserLog(joinPoint, null, jsonResult);
//    }
//
//    /**
//     * 异常操作
//     * @param joinPoint
//     * @param exception
//     */
//    @AfterThrowing(pointcut = "userLogPointCut()", throwing = "exception")
//    public void handleAfterThrowing(JoinPoint joinPoint, Exception exception){
//        handleUserLog(joinPoint, exception, null);
//    }
//
//    /**
//     * 具体增强方法
//     * @param joinPoint
//     * @param exception
//     * @param jsonResult
//     */
//    private void handleUserLog(final JoinPoint joinPoint, final Exception exception, Object jsonResult) {
//        try {
//            //获得注解
//            UserLog userLog = getUserLog(joinPoint);
//            if (userLog == null) {
//                return;
//            }
//            AdUserLog adUserLog = new AdUserLog();
//
//            //获取当前用户
//            AdUser adUser = adUserMapper.selectById(AdminThreadLocalUtil.getUser().getId());
//            if (adUser != null) {
//                adUserLog.setUsername(adUser.getName());
//            }
//
//            //获取请求参数
//            String param = Arrays.toString(joinPoint.getArgs());
//            if (StringUtils.isNotBlank(param)) {
//                adUserLog.setParam(param);
//            }
//
//            //获取返回结果
//            adUserLog.setJsonResult(JSON.toJSONString(jsonResult));
//
//            //异常
//            if (exception != null) {
//                adUserLog.setStatus((short) 0);
//                adUserLog.setErrorMessage(exception.getMessage().substring(0,1000));
//            }
//
//            //操作方法名
//            String className = joinPoint.getTarget().getClass().getName();
//            String methodName = joinPoint.getSignature().getName();
//            adUserLog.setMethod(className + "." + methodName + "()");
//
//            //获取注解上的信息
//            adUserLog.setType(userLog.type());
//            adUserLog.setModule(userLog.module());
//
//            //操作时间
//            adUserLog.setCreatedTime(new Date());
//
//            //保存操作日志
//            adUserLogMapper.insert(adUserLog);
//        } catch (Exception e) {
//            log.info("日志有误");
//            e.printStackTrace();
//        }
//
//    }
//
//    /**
//     * 是否存在注解，存在就获取
//     * @param joinPoint
//     * @return
//     */
//    private UserLog getUserLog(JoinPoint joinPoint) {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        if (method != null) {
//            return method.getAnnotation(UserLog.class);
//        }
//        return null;
//    }
//}
