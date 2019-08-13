package com.ysc.device.service.exception;

/**
 *
 *
 * @author yusicong
 * @Date 2017/7/6
 * @Time 下午2:54
 * @Description
 */
public interface BaseExceptionCode {
    /**
     * 获取错误标识符
     *
     * @return
     */
    String getErrorCode();

    /**
     * 获取错误消息
     *
     * @return 错误消息
     */
    String getErrorMessage();

    /**
     * 获取错误提示符
     * 用于页面错误信息展示使用
     *
     * @return 错误提示符
     */
    String getErrorTips();
}
