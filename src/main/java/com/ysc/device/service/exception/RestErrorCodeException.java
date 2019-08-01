package com.ysc.device.service.exception;

/**
 * @author enmonster
 * @Date 2017/7/20
 * @Time 下午14:43
 * @Description
 */
public class RestErrorCodeException extends BaseException {

    public RestErrorCodeException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }
}
