package com.dogmall.demo.kakaologin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogmall.demo.member.MemberService;
import com.dogmall.demo.member.SNSUserDto;
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
		
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		if(kakaoLoginVO != null) {
			
			log.info("사용자정보:" + kakaoLoginVO);
			
		
			//인증을 세션방식으로 처리.
			session.setAttribute("kakao_status", kakaoLoginVO); // 인증여부에 사용
			session.setAttribute("accessToken", accessToken);  // 카카오 로그아웃에 사용
			
			String sns_email = kakaoLoginVO.getEmail();
			
			String sns_login_type = memberService.existsUserInfo(sns_email);
//			session.setAttribute("sns_type", sns_login_type);
			
			if(memberService.existsUserInfo(sns_email) == null && memberService.sns_user_check(sns_email) == null) {
				//SNS테이블 데이타 삽입작업.
				SNSUserDto dto = new SNSUserDto();
				dto.setSns_id(kakaoLoginVO.getId().toString());
				dto.setSns_email(kakaoLoginVO.getEmail());
				dto.setSns_nickname(kakaoLoginVO.getNickname());
				dto.setSns_type("kakao");
				
				
				memberService.sns_user_insert(dto);
			}
			
		}
		
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
