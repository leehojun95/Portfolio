package com.dogmall.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.dogmall.demo.member.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

	// 그리고 리턴값이 true가 되면, Controller로 진행이 이루어진다.
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			
			log.info("preHandle");
			
			boolean result = false;
			
			HttpSession session = request.getSession();
			MemberVO vo = (MemberVO)session.getAttribute("login_status");
			
			if(vo == null) {	// 요청이 인증되지 않은 상태를 의미
				
				result = false;
				
				if(isAjaxRequest(request)) {	// ajax요청이라는 의미.
					response.sendError(400);
				}else {
					// 원래 요청한 주소를 세션으로 저장하는 기능.
					getTargetUrl(request);
					response.sendRedirect("/user/login");
				}

			}else {
				result = true;
			}
			
			
			return result;
		}
		
		//사용자 요청이 ajax요청인지 구분
		private boolean isAjaxRequest(HttpServletRequest request) {
			boolean isAjax = false;
			
			String header = request.getHeader("AJAX");
			if(header != null && header.equals("true")) {
				isAjax = true;
			}
			return isAjax;
		}
		
		// 원래요청한 주소
		private void getTargetUrl(HttpServletRequest request) {
			
			String uri = request.getRequestURI();	// /userinfo/mypage
			String query = request.getQueryString();	// ?물은표뒤의 문자열. ?userid=doccomsa
			
			if(query == null || query.equals("null")) {
				query = "";
			}else {
				query = "?" + query;
			}
			
			String targetUrl = uri + query;
			
			if(request.getMethod().equals("GET")) {
				request.getSession().setAttribute("targetUrl", targetUrl);
			}
		}
}
