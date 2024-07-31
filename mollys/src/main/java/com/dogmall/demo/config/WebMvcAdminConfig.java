
package com.dogmall.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dogmall.demo.interceptor.AdminInterceptor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class WebMvcAdminConfig implements WebMvcConfigurer {

	private final AdminInterceptor adminInterceptor;
	
	//인터셉터 매핑주소 설정에 제외되는 경로작업.	즉 인터셉터가 동작되지 않게 하는 주소.
	private static final String[] EXCLUDE_PATHS = {
		"/admin/category/secondcategory/{ctg_l_code}",
		"/admin/",
		"/admin/adminlogin"	// 로그인을 클릭하면 이주소를거쳐서 로그인이 되기때문에(로그인확인작업) 인터셉터를 하면 다시 로그인상태로 돌아간다.
	};
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminInterceptor)
				.addPathPatterns("/admin/**")	//admin으로 시작하는 하위레벨의 깊이에 상관없이 모든주소를 설정.
				.excludePathPatterns(EXCLUDE_PATHS);	//위의 설정에서 제외되는 주소설정.
	}
}
