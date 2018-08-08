package com.ysc.device.service.aop.log.annotation;

import java.lang.annotation.*;

/**
 * Created by IDEA
 *
 * @author : yusicng
 * @Date : 2018/8/8.
 * @Time : 17:25
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public  @interface ControllerLogger {
}
