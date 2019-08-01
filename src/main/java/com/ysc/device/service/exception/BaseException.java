package com.ysc.device.service.exception;

import java.util.Map;

/**
 *
 *
 * @author enmonster
 * @Date 2017/7/6
 * @Time 下午2:54
 * @Description
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -3962157388406613297L;
    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误前台描述
     */
    private String errorTips;

    private Map<String, String> errorFieldMap;

    private BaseExceptionCode baseErrorCode;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(String errorCode, String message, String errorTips) {
        super(message);
        this.errorCode = errorCode;
        this.errorTips = errorTips;
    }

    public BaseException(BaseExceptionCode baseErrorCode, String message){
        super(message);
        this.baseErrorCode = baseErrorCode;
    }
    public BaseException(BaseExceptionCode baseErrorCode, String message, String errorTips){
        super(message);
        this.baseErrorCode = baseErrorCode;
        this.errorTips = errorTips;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(BaseExceptionCode baseErrorCode){
        this.baseErrorCode = baseErrorCode;
    }

    public BaseException(BaseExceptionCode baseErrorCode, String message, Throwable cause){
        super(message,cause);
        this.baseErrorCode = baseErrorCode;
    }

    public BaseException(String errorCode, String message, String errorTips, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorTips = errorTips;
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(BaseExceptionCode baseErrorCode, Throwable cause) {
        super(cause);
        this.baseErrorCode = baseErrorCode;
    }

    public BaseException(BaseExceptionCode baseErrorCode, Map<String, String> errorFieldMap){
        super();
    	this.baseErrorCode = baseErrorCode;
    	this.errorFieldMap = errorFieldMap;
    }

    public String getErrorCode() {
        return errorCode;
    }

	public String getErrorTips() {
		return errorTips;
	}

	public BaseExceptionCode getBaseErrorCode() {
		return baseErrorCode;
	}
	
	public boolean hasFlashErrorCode(){
		return baseErrorCode !=null;
	}
}
