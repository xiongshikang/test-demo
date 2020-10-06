package com.test.demo.config;

import com.test.demo.handler.DefaultGlobalExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;

/***
* @Description: 全局异常
* @Param:
* @return:
* @Author: John
* @Date: 2020/9/30 16:29
**/
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class ExceptionConfiguration extends DefaultGlobalExceptionHandler {
}
