package com.dogmall.demo.naverlogin;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogmall.demo.member.MemberService;
import com.dogmall.demo.member.SNSUserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/oauth2")
@Slf4j
@Controller
public class NaverLoginController {
	
	private final NaverLoginService naverLoginService;
	private final MemberService memberService;
	
	@GetMapping("naverlogin")
	public String connect() throws UnsupportedEncodingException {
		
		String url = naverLoginService.getNaverAuthorizeUrl();
		
		return "redirect:" + url;
	}
	
	@GetMapping("/callback/naver")
	public String callback(NaverCallback callback, HttpSession session) throws UnsupportedEncodingException, Exception {
		
		if(callback.getError() != null) {
			log.info(callback.getError_description());
		}
		
		String responseToken = naverLoginService.getNaverTokenUrl(callback);
		
		ObjectMapper objectMapper = new ObjectMapper();
		NaverToken naverToken = objectMapper.readValue(responseToken, NaverToken.class);
		
		log.info("토큰정보: " + naverToken.toString());
		
		String responseUser =  naverLoginService.getnaverUserByToken(naverToken);
		NaverResponse naverResponse = objectMapper.readValue(responseUser, NaverResponse.class);
		
		log.info("사용자정보: " + naverResponse.toString());
		
		String sns_email = naverResponse.getResponse().getEmail();
		//
		if(naverResponse != null) {
			session.setAttribute("naver_status", naverResponse);
			session.setAttribute("accessToken", naverToken.getAccess_token());
			//
			if(memberService.existsUserInfo(sns_email) == null && memberService.sns_user_check(sns_email) == null) {
			
				SNSUserDto dto = new SNSUserDto();
				dto.setSns_id(naverResponse.getResponse().getId());
				dto.setSns_email(naverResponse.getResponse().getEmail());
				dto.setSns_nickname(naverResponse.getResponse().getName());
				dto.setSns_type("naver");
				
				memberService.sns_user_insert(dto);
				
			}
			
			
		}
		
		
		return "redirect:/";
	}
	
	   @GetMapping("/naverlogout")
	    public String naverlogout(HttpSession session) throws JsonProcessingException {
	        String accessToken = (String) session.getAttribute("accessToken");


	        naverLoginService.getNaverTokenDelete(accessToken);

			if(accessToken != null && !"".equals(accessToken)) {
//				try {
//					kakaoLoginService.kakaologout(accessToken);
//				}catch(JsonProcessingException ex) {
//					throw new RuntimeException(ex);
//				}
				
//				session.invalidate();
				
				session.removeAttribute("naver_status");
				session.removeAttribute("accessToken");
	        }


	        return "redirect:";
	    }
	

}
