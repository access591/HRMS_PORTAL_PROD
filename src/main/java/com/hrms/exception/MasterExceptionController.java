package com.hrms.exception;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MasterExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(MasterExceptionController.class);

	@ExceptionHandler(SQLException.class)
	public String handleSqlException(HttpServletRequest httpServletRequest, Exception ex) {
	
		return "database_error";
	}

	@ExceptionHandler(Exception.class)
	public String handleIOException(HttpServletRequest httpServletRequest, Exception ex) {
		logger.error("IOException handler executed");
		return "input_output_exception";
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ModelAndView userNotFoundException(HttpServletRequest httpServletRequest, Exception ex) {
		logger.error("userNotFoundException handler executed");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", ex);
		modelAndView.addObject("url", httpServletRequest.getRequestURL());
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
