package com.h.common.exception;



import com.h.model.common.dtos.ResponseResult;
import com.h.model.common.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice  //控制器增强类,标明为处理全局异常
@Slf4j
public class ExceptionCatch {

    /**
     * 处理不可控异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception e){
        e.printStackTrace();
        log.error("未知异常，出现的异常原因:{}",e.getMessage());
        //通知相关人员，解决问题
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
    }

    /**
     * 处理可控异常  自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult exception(CustomException e){
        log.error("自定义异常处理，出现的异常:{}",e);
        return ResponseResult.errorResult(e.getAppHttpCodeEnum());
    }
}
