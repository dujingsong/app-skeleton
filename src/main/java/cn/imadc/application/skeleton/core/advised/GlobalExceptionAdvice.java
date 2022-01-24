package cn.imadc.application.skeleton.core.advised;

import cn.imadc.application.base.common.exception.NotLoginException;
import cn.imadc.application.base.common.exception.UnauthorizedException;
import cn.imadc.application.base.common.response.ResponseW;
import cn.imadc.application.skeleton.core.data.code.MsgCode;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 * 全局异常处理
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-24
 */
@Order(1)
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 未登录异常处理
     *
     * @param exception 异常数据
     * @return JSON格式错误响应数据
     */
    @ResponseBody
    @ExceptionHandler(value = NotLoginException.class)
    public ResponseW handleNotLoginException(NotLoginException exception) {
        return new ResponseW(MsgCode.API_000001, "未登录");
    }

    /**
     * 未授权异常处理
     *
     * @param exception 异常数据
     * @return JSON格式错误响应数据
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ResponseW handleException(UnauthorizedException exception) {
        return new ResponseW(MsgCode.API_000002, "没有权限");
    }

}
