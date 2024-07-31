package com.dogmall.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dogmall.demo.interceptor.LoginInterceptor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component	//bean 생성
public class WebMvcLonginConfig implements WebMvcConfigurer {

	private final LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor((HandlerInterceptor) loginInterceptor)
				.addPathPatterns(
								 "/user/modify",
								 "/user/changpw",
								 "/user/delete",
								 "/cart/cart_list",
								 "/order/orderinfo",
								 "/order/ordercomplete"
								 );
		
		// 아이디가 필요한 부분에 주소만(Get방식) 정한다. or 주소를 치고 들어올수도있기때문에 Httpsession이 적용된 것을 적어주면된다.
		/*
		 특정주소가 인증된 경우에만 사용 할 때 : /userinfo/*(바로 다음 userinfo -> 파일), /userinfo/**(모든 주소 userinfo -> 파일 -> 파일 -> ....)
		 */
	}
}
