package com.microservice.ged.filter;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.microservice.ged.beans.Trace;
import com.microservice.ged.repository.TraceRepository;

@Component
public class TraceInterceptor implements HandlerInterceptor {

	@Autowired
	private TraceRepository tracerepository;



	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		String user = "Visitor";



		Trace t = new Trace(user, request.getRequestURI(), request.getRequestURL().toString(),
				null, request.getMethod(), response.getStatus());


		if (request.getHeader("x-forwarded-for") == null) {
			t.setIp(request.getRemoteAddr());
		}else{
			t.setIp(request.getHeader("x-forwarded-for"));
		}


		if (request.getUserPrincipal() == null) {
			tracerepository.save(t);
		} else {
			t.setUsername(request.getUserPrincipal().getName());
			tracerepository.save(t);
		}

	}

}

