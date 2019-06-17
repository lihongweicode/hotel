package org.dppc.vv.common.exception;

import org.apache.log4j.Logger;
import org.dppc.vv.common.model.ResultDTO;
import org.dppc.vv.plugin.token.TokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
/**
 * 全局异常处理器
 * @author Gaojun.Zhou
 * @date 2016年12月30日 上午11:48:47
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

	public static final Logger logger = Logger.getLogger(ExceptionAdvice.class);

	  /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResultDTO handleValidationException(ValidationException e) {
        logger.error("参数验证失败", e);
        return ResultDTO.customFail("参数验证失败");
    }
	 /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败", e);
        return ResultDTO.customFail("参数解析失败");
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultDTO handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e,HttpServletRequest request) {
        logger.error("不支持当前请求方法", e);
        return ResultDTO.customFail(String.format("不支持%s请求方式",request.getMethod()));
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultDTO handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        return ResultDTO.customFail("不支持当前媒体类型");
    }

    /**
     * 404 - Not Found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultDTO handleNoHandlerFoundException(NoHandlerFoundException  e) {
        logger.error("资源不存在", e);
        return ResultDTO.customFail("资源不存在");
    }

    /**
     * 500 - NullPointerException Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ResultDTO handleNullPointerException(NullPointerException e) {
        logger.error("空指针异常", e);
        return ResultDTO.customFail("空指针异常");
    }
    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResultDTO handleException(Exception e) {
        if(e instanceof TokenException){
            return ResultDTO.tokenFail(e.getMessage());
        }
        logger.error("服务运行异常,"+e.getMessage(), e);
        return ResultDTO.customFail(e.getMessage());
    }
}
