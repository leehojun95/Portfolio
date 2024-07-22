package com.dogmall.demo.kakaopay;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dogmall.demo.Order.OrderService;
import com.dogmall.demo.Order.OrderVO;
import com.dogmall.demo.cart.CartProductVO;
import com.dogmall.demo.cart.CartService;
import com.dogmall.demo.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/kakao/*")
@Controller
public class KakaopayController {

	private final KakaopayService kakaopayService;
	private final CartService cartService;
	private final OrderService orderService;
	
	private OrderVO vo;
	
	private String mbl_id;
	
	
	@GetMapping("/kakaoPayRequest")
	public void kakaoPayRequest() {
		
	}
	
	
	@ResponseBody
	@GetMapping(value =  "/kakaoPay")
	public ReadyResponse kakaoPay(OrderVO vo, HttpSession session) {
		
		log.info("주문자정보1: " + vo);
		
		// 1)결제준비요청(ready)
		// ready(String partnerOrderId, String partnerUserId, String itemName, int quantity, 
		//          int totalAmount, int taxFreeAmount, int vatAmount)
		
		String mbl_id = ((MemberVO) session.getAttribute("login_status")).getMbl_id();
		this.mbl_id = mbl_id;
		
		List<CartProductVO> cart_list = cartService.cart_list(mbl_id);
		
		String itemName = "";
		int quantity = 0;
		int totalAmount = 0;
		int taxFreeAmount = 0;
		int vatAmount = 0;
		
		for(int i=0; i < cart_list.size(); i++) {
			itemName += cart_list.get(i).getPro_name() + "/";
			quantity += cart_list.get(i).getCart_amount();
			totalAmount += cart_list.get(i).getPro_price() * cart_list.get(i).getCart_amount();
		}
		
		String partnerOrderId = mbl_id;
		String partnerUserId = mbl_id;
				
		ReadyResponse readyResponse = kakaopayService.ready(partnerOrderId, partnerUserId, itemName, quantity, 
				          totalAmount, taxFreeAmount,  vatAmount);
		
		log.info("응답데이터: " + readyResponse);
		
		// 주문정보
		this.vo = vo;
		
		return readyResponse;
	}
	
	// 성공
	@GetMapping("/approval")
	public void approval(String pg_token) {
		log.info("pg_token: " + pg_token);
		
		// 2)결제승인요청
		String approveResponse = kakaopayService.approve(pg_token);
		log.info("최종결과: " + approveResponse);
		// 주문정보저장
		// aid값이 존재하면
		
		// 트랜잭션으르 처리 : 주문테이블, 주문상세테이블, 결제테이블, 장바구니 비우기
		if(approveResponse.contains("aid")) {
			log.info("주문자정보2: " + vo);
			orderService.order_process(vo, mbl_id, "kakaopay", "완료", "kakaopay");
		}
		
		
		
	}
	// 취소
	@GetMapping("/cancel")
	public void cancel() {
		
		
	}
	
	// 실패
	@GetMapping("/fail")
	public void fail() {
		
		
	}
}
