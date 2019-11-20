package com.human.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter {

	@Override
	// controller 진입하기 전에 실행
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println(">> preHandle in");

		HttpSession session = request.getSession();
		Object obj = session.getAttribute("id"); // 객체의 타입을 모를때는 Object로 받습니다.
		if (obj == null) {
			System.out.println("Obj" + obj);
			response.sendRedirect(request.getContextPath() + "/love/Join");
			return false;
			// **controller에 가지않고 멈춰버림
		}
		System.out.println(">> preHandle out");
		return true;
		// **controller에 넘어감
	}

//	@Override
//	// controller 진입 후에 실행
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println(">> postHandle in");
//
//		System.out.println(">> postHandle out");
//		super.postHandle(request, response, handler, modelAndView);
//	}

}
