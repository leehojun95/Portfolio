package com.dogmall.demo.Order;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogmall.demo.cart.CartProductVO;
import com.dogmall.demo.cart.CartService;
import com.dogmall.demo.cart.CartVO;
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
	
	@GetMapping("/orderinfo")
	public String orderinfo (CartVO vo, Model model, HttpSession session) throws Exception {
		
		String mbl_id = ((MemberVO) session.getAttribute("login_status")).getMbl_id();
		vo.setMbl_id(mbl_id);
		
		cartService.cart_add(vo);
		
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

}
