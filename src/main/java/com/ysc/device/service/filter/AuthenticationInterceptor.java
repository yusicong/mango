package com.ysc.device.service.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ysc.device.service.Application;
import com.ysc.device.service.domain.entities.UserEntity;
import com.ysc.device.service.domain.enumes.BaseErrorCodeEnum;
import com.ysc.device.service.service.UserService;
import com.ysc.device.service.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author yusicong
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(Application.PassToken.class)) {
            Application.PassToken passToken = method.getAnnotation(Application.PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(Application.UserLoginToken.class)) {
            Application.UserLoginToken userLoginToken = method.getAnnotation(Application.UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException(BaseErrorCodeEnum.SYStem_ERROR_1.getValue()+"");
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException(BaseErrorCodeEnum.SYStem_ERROR_3.getValue()+"");
                }
                UserEntity user = userService.findUserById(userId);
                System.out.println(JsonUtils.toJSONString(user));
                if (user == null) {
                    throw new RuntimeException(BaseErrorCodeEnum.LOGIN_STATUS_2.getValue()+"");
                }
                // 验证 token
                System.out.println("password是："+user.getPassword());
                if (StringUtils.isBlank(user.getPassword())){
                    user.setPassword("a96500909");
                }
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException(BaseErrorCodeEnum.SYStem_ERROR_3.getValue()+"");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}