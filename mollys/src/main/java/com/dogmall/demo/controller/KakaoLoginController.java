package com.dogmall.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogmall.demo.domain.KakaoLoginVO;
import com.dogmall.demo.service.KakaoLoginService;
import com.dogmall.demo.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/oauth2")
@Controller
public class KakaoLoginController {

	private final KakaoLoginService kakaoLoginService;
	private final MemberService memberService;
	
	@Value("${kakao.client.id}")
	private String clientId;
	
	@Value("${kakao.redirect.uri}")
	private String redirectUri;
	
	@Value("${kakao.client.secret}")
	private String clientSecret;
	
	@GetMapping("/kakaologin")
	public String connect() {
		
		StringBuffer url = new StringBuffer();
		url.append("https://kauth.kakao.com/oauth/authorize?");
		url.append("response_type=code");
		url.append("&client_id=" + clientId);
		url.append("&redirect_uri=" + redirectUri);
		url.append("&prompt=login");
		
		log.info("인가코드: " + url.toString());
		
		return "redirect:" + url.toString();
	}
	
	@GetMapping("/callback/kakao")
	public String callback(String code, HttpSession session) {
		
		log.info("code:" + code);
		
		String accessToken= "";
		KakaoLoginVO kakaoLoginVO = null;
		
		try {
			
			accessToken = kakaoLoginService.getAccessToken(code);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			kakaoLoginVO = kakaoLoginService.getKakaoLoginVO(accessToken);
			

			session.setAttribute("kakao_status", kakaoLoginVO);
			session.setAttribute("accessToken", accessToken);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		log.info("사용자정보: " + kakaoLoginVO);
		
		return "redirect:/";
	}
	
	
	@GetMapping("/kakaologout")
	public String kakaologout(HttpSession session) {
		
		String accessToken = (String) session.getAttribute("accessToken");
		
		if(accessToken != null && !"".equals(accessToken)) {
			try {
				kakaoLoginService.kakaologout(accessToken);
			}catch(JsonProcessingException ex) {
				throw new RuntimeException(ex);
			}
			
			session.removeAttribute("kakao_status");
			session.removeAttribute("accessToken");
		}
		
		return "redirect:/";
	}
}
