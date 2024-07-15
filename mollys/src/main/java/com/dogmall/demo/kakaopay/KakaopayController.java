package com.dogmall.demo.kakaopay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/kakao/*")
@Controller
public class KakaopayController {

	private final KakaopayService kakaopayService;
	
	
	@GetMapping("/kakaoPayRequest")
	public void kakaoPayRequest() {
		
	}
	
	
	@ResponseBody
	@GetMapping(value =  "/kakaoPay")
	public ReadyResponse kakaoPay() {
		
		//1) 결재준비요청
		ReadyResponse readyResponse = kakaopayService.ready();
		
		log.info("응답데이터: " + readyResponse);
		
		return readyResponse;
	}
	
	@GetMapping("/approval")
	public void approval(String pg_token) {
		log.info("pg_token: " + pg_token);
		
		// 2) 결제승인요청
		String approveResponse = kakaopayService.approve(pg_token);
		
		log.info("최종결과: " + approveResponse);
	}
	
	// 취소
	@GetMapping("/cancel")
	public void cancel() {
		
		
	}
	
	
	//실패
	@GetMapping("/fail")
	public void fail() {
		
		
	}
}
