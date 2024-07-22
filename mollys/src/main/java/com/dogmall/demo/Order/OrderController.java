package com.dogmall.demo.Order;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dogmall.demo.cart.CartProductVO;
import com.dogmall.demo.cart.CartService;
import com.dogmall.demo.cart.CartVO;
import com.dogmall.demo.member.MemberService;
import com.dogmall.demo.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/order/")
@Slf4j
@Controller
public class OrderController {
	
	private final OrderService orderService;
	private final CartService cartService;
	private final MemberService memberService;
	
	@GetMapping("/orderinfo")
	public String orderinfo (@RequestParam(value = "type", defaultValue = "direct") String type, CartVO vo, Model model, HttpSession session) throws Exception {
		
		String mbl_id = ((MemberVO) session.getAttribute("login_status")).getMbl_id();
		vo.setMbl_id(mbl_id);
		
		if(!type.equals("cartorder")){
		cartService.cart_add(vo);
		}
		
		List<CartProductVO> cart_list = cartService.cart_list(mbl_id);
		int total_price = 0;
		cart_list.forEach(d_vo ->{
			d_vo.setPro_up_folder(d_vo.getPro_up_folder().replace("\\", "/"));
			}
		);
		
		for(int i=0; i < cart_list.size(); i++) {
			total_price += (cart_list.get(i).getPro_price() * cart_list.get(i).getCart_amount());
		}
		
		model.addAttribute("cart_list", cart_list);
		model.addAttribute("total_price", total_price);
		
		return "/order/orderinfo";
	}
	
	@GetMapping("/sameorder")
	public ResponseEntity<MemberVO> sameorder(HttpSession session) throws Exception{
		ResponseEntity<MemberVO> entity = null;
		
		String mbl_id = ((MemberVO) session.getAttribute("login_status")).getMbl_id();
		
		entity = new ResponseEntity<MemberVO>(memberService.login(mbl_id), HttpStatus.OK);
		
		return entity;
	}
	
	@PostMapping("/ordersave")
	public String ordersave (OrderVO vo, String pay_nobank, String pay_nobank_user, HttpSession session) throws Exception{
		
		log.info("주문정보: " + vo);
		log.info("입금은행: " + pay_nobank);
		log.info(("예금주:" + pay_nobank_user));
		
		String mbl_id = ((MemberVO) session.getAttribute("login_status")).getMbl_id();
		
		vo.setMbl_id(mbl_id);
		
		String payinfo = pay_nobank + "/" + pay_nobank_user;
		
		orderService.order_process(vo, mbl_id, "무통장입금", "미납", payinfo);
		
		return "redirect:/order/ordercomplete";
	}
	
	@GetMapping("/ordercomplete")
	public void ordercomplete() throws Exception{
		
	}

}
