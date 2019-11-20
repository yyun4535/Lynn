package com.human.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter {

	@Override
	// controller �����ϱ� ���� ����
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println(">> preHandle in");

		HttpSession session = request.getSession();
		Object obj = session.getAttribute("id"); // ��ü�� Ÿ���� �𸦶��� Object�� �޽��ϴ�.
		if (obj == null) {
			System.out.println("Obj" + obj);
			response.sendRedirect(request.getContextPath() + "/love/Join");
			return false;
			// **controller�� �����ʰ� �������
		}
		System.out.println(">> preHandle out");
		return true;
		// **controller�� �Ѿ
	}

//	@Override
//	// controller ���� �Ŀ� ����
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println(">> postHandle in");
//
//		System.out.println(">> postHandle out");
//		super.postHandle(request, response, handler, modelAndView);
//	}

}
