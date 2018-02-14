package com.my.framework.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class CustomSImpleMappingExceptionResolver extends SimpleMappingExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		if (isAjax(request)) {
			response.reset();
			response.setStatus(HttpServletResponse.SC_OK);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json");
			
			String errorCode = "-1";
			if (getExceptionMessage(ex) != null && getExceptionMessage(ex).equals("403")) {
				errorCode = "-2";
			}
			
			MappingJackson2JsonView view = new MappingJackson2JsonView();
			Result result = new Result("500", errorCode, getExceptionMessage(ex), getStackTrace(ex));
			
			view.setAttributesMap(result.getMap());
			return new ModelAndView(view);
			
		} else {
			return super.resolveException(request, response, handler, ex);
		}
	}

	public String getStackTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter, true);
		t.printStackTrace(printWriter);
		printWriter.flush();
		stringWriter.flush();
		
		return stringWriter.toString();
	}
	
	private String getExceptionMessage(Throwable e) {
		String message = "";
		
		if (e != null) {
			message += e.getMessage();
		}
		
		logger.error("exception message : " + message);
		
		return message;
	}
	
	private boolean isAjax(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}
	
	private String getMethod(Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		return handlerMethod.getMethod().getName();
	}
	
	
}
