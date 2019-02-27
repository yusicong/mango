package com.ysc.device.service.domain.response;




import com.ysc.device.service.exception.BaseException;
import com.ysc.device.service.exception.BaseExceptionCode;

import java.io.Serializable;

/**
 * BaseResponse
 *
 * @Author Chase Lv(蛰龙)
 * @Date 2017/7/7
 * @Time 上午7:30
 * @Description
 */
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -414005101985309470L;
    /**
     * 是否成功
     */
    private boolean success = false;

    /**
     * 字符型错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 前端页面错误提示
     */
    private String errorTips;


    /**
     * 处理成功的业务数据
     */
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorTips() {
        return errorTips;
    }

    public void setErrorTips(String errorTips) {
        this.errorTips = errorTips;
    }


    public BaseResponse<T> fail(BaseExceptionCode errorCode) {
        this.setSuccess(false);
        this.setErrorCode(errorCode.getErrorCode());
        this.setErrorMessage(errorCode.getErrorMessage());
        this.setErrorTips(errorCode.getErrorTips());
        return this;
    }

    public BaseResponse<T> fail(BaseExceptionCode errorCode, String appendMessage) {
        this.setSuccess(false);
        this.setErrorCode(errorCode.getErrorCode());
        this.setErrorMessage(errorCode.getErrorMessage() + ":" + appendMessage);
        this.setErrorTips(errorCode.getErrorTips() + ":" + appendMessage);
        return this;
    }

    public BaseResponse<T> fail(BaseException baseException) {
        this.setSuccess(false);
        this.setErrorCode(baseException.getErrorCode());
        this.setErrorMessage(baseException.getMessage());
        return this;
    }

    public BaseResponse<T> success(T model) {
        this.setSuccess(true);
        this.setData(model);
        return this;
    }

    /**
     * 获取成功的结果
     *
     * @param model 业务数据
     * @param <T>   业务数据的范型
     * @return
     */
    public static <T> BaseResponse<T> createSuccessResult(T model) {
        BaseResponse<T> rt = new BaseResponse<T>();
        return rt.success(model);
    }

    /**
     * 创建失败的结果
     * 不存在附加错误信息的情况下
     *
     * @param errorCode 错误编码
     * @param isRetry   是否重试
     * @return 结果对象
     */
    public static <T> BaseResponse<T> createFailResult(BaseExceptionCode errorCode) {
        BaseResponse<T> rt = new BaseResponse<T>();
        return rt.fail(errorCode);
    }

    /**
     * 获取失败的结果
     * 存在附加信息的情况下,使用此方法
     *
     * @param errorCode     错误编码
     * @param appendMessage 附加错误消息
     * @param isRetry       是否重试
     * @return 结果对象
     */
    public static <T> BaseResponse<T> createFailResult(BaseExceptionCode errorCode, String appendMessage) {
        BaseResponse<T> rt = new BaseResponse<T>();
        return rt.fail(errorCode, appendMessage);
    }
}
